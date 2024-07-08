package artgarden.server.kopis.service;

import artgarden.server.common.util.UtilBean;
import artgarden.server.performance.entity.Performance;
import artgarden.server.rank.entity.WeeklyRank;
import artgarden.server.performance.entity.dto.PerformanceApiDTO;
import artgarden.server.rank.entity.dto.RankApiDto;
import artgarden.server.performance.repository.PerformanceRepository;
import artgarden.server.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class KopisServiceImpl implements  KopisService{

    private final PerformanceRepository performanceRepository;
    private final RankRepository rankRepository;

    //오늘~ 한달 뒤 공연 정보 업데이트
    @Transactional
    public void updateUpcoming(String standardDate){
        List<Performance> performanceList = getPerformanceList(UtilBean.formatDate(LocalDate.now()), standardDate, "01");   //01: 공연예정
        performanceRepository.saveAll(performanceList);
    }

    //오늘 공연중인 정보 업데이트
    @Transactional
    public void updateOngoing(){
        List<Performance> performanceList = getPerformanceList(UtilBean.formatDate(LocalDate.now()), UtilBean.formatDate(LocalDate.now()), "02"); //02: 공연중
        performanceRepository.saveAll(performanceList);
    }

    //standardDate 이전 공연 정보 삭제
    @Transactional
    public void deletePerformed(LocalDate standardDate){
        performanceRepository.deleteByEndDateBefore(standardDate);
    }

    //endDate가 지난 공연 상태 공연완료로 변경
    @Transactional
    public void updatePerformStatus(){
        performanceRepository.updatePerformStatusForExpiredPerformances(LocalDate.now());
    }

    @Transactional
    public void updateRank(String ststype, String rankDate){
        WeeklyRank weeklyRank = getRank(ststype, rankDate);
        WeeklyRank checkWeeklyRank = rankRepository.findByRankDate(weeklyRank.getRankDate());
        if(checkWeeklyRank != null){
            checkWeeklyRank.updateFromRank(weeklyRank);
            rankRepository.save(checkWeeklyRank);
        }else{
            rankRepository.save(weeklyRank);
        }
        System.out.println("[랭킹 업데이트 날짜] : " + rankDate);
    }

    @Transactional
    public void saveSinglePerformance(String performId){
        Performance performance = getSinglePerformance(performId);
        performanceRepository.save(performance);
    }

    @Transactional
    public void updateAreaCode(){
        performanceRepository.updateAreaCode();
    }

    @Transactional
    public void updateGenreCode(){
        performanceRepository.updateGenreCode();
    }

    @Transactional
    public List<Performance> getPerformanceList(String startDate, String endDate, String performStatus){
        // performId가 모두 저장되는 String 리스트
        HashMap<String, String> performIdList = getPerformanceId(startDate, endDate, performStatus);

        //OpenApi에서 performId를 이용해 performDetail을 리스트에 저장
        return getPerformanceDetail(performIdList);
    }

    private HashMap<String, String> getPerformanceId(String startDate, String endDate, String performStatus) {
        HashMap<String, String> performIdList = new HashMap<String, String>();
        int cpage = 1;
        while(true) {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                    .path("/openApi/restful/pblprfr")
                    .queryParam("service", "86fdb34b92254e1b84343a5c323e3314")
                    .queryParam("stdate", startDate)
                    .queryParam("eddate", endDate)
                    .queryParam("cpage", cpage)
                    .queryParam("rows", 1000)
                    .queryParam("prfstate", performStatus)
                    .queryParam("newsql", "Y")
                    .encode()
                    .build()
                    .toUri();

            String url = uri.toString();
            String responseBody = restTemplate.getForEntity(url, String.class).getBody();
            //xnmlParsing
            HashMap<String, String> newIdList = idXmlParsing(responseBody);
            //endPoint check
            if (CollectionUtils.isEmpty(newIdList)) {
                break;
            }

            performIdList.putAll(newIdList);

            cpage++;
        }

        return performIdList;
    }

    private List<Performance> getPerformanceDetail(HashMap<String, String> performIdList){
        List<Performance> performances = new ArrayList<>();

        for(String id : performIdList.keySet()){
            Performance performance = new Performance();
            RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                    .path("/openApi/restful/pblprfr/{id}")
                    .queryParam("service","86fdb34b92254e1b84343a5c323e3314")
                    .queryParam("newsql","Y")
                    .buildAndExpand(id)
                    .toUri();
            String url = uri.toString();
            String responseBody = restTemplate.getForEntity(url, String.class).getBody();
            //xmlParsing
            performance.updateFromApiDto(detailXmlParsing(responseBody, performIdList));
            performances.add(performance);
        }

        return performances;
    }

    private Performance getSinglePerformance(String performId){

        Performance performance = new Performance();
        HashMap<String,String> map = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                .path("/openApi/restful/pblprfr/{id}")
                .queryParam("service", "86fdb34b92254e1b84343a5c323e3314")
                .queryParam("newsql", "Y")
                .buildAndExpand(performId)
                .toUri();
        String url = uri.toString();
        String responseBody = restTemplate.getForEntity(url, String.class).getBody();
        //xmlParsing
        performance.updateFromApiDto(detailXmlParsing(responseBody, map));
        return performance;
    }

    private WeeklyRank getRank(String ststype, String rankDate) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                .path("/openApi/restful/boxoffice")
                .queryParam("service", "86fdb34b92254e1b84343a5c323e3314")
                .queryParam("ststype", ststype)
                .queryParam("date", rankDate)
                .queryParam("newsql", "Y")
                .encode()
                .build()
                .toUri();

        String url = uri.toString();
        String responseBody = restTemplate.getForEntity(url, String.class).getBody();
        RankApiDto dto = rankXmlParsing(responseBody, UtilBean.formatString(rankDate));
        WeeklyRank weeklyRank = new WeeklyRank();
        weeklyRank.updateFromApiDto(dto);

        return weeklyRank;
    }

    private HashMap<String, String> idXmlParsing(String responsebody){
        try {
            HashMap<String, String> map = new HashMap<>();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responsebody.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList itemList = root.getElementsByTagName("db");

            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);
                NodeList mt20idNodeList = item.getElementsByTagName("mt20id");
                NodeList areaNodeList = item.getElementsByTagName("area");

                String mt20idValue = "";
                String areaValue = "";
                if (mt20idNodeList.getLength() > 0) {
                    Element mt20idElement = (Element) mt20idNodeList.item(0);
                    mt20idValue = mt20idElement.getTextContent();
                }
                if(areaNodeList.getLength() > 0){
                    Element areaElement = (Element) areaNodeList.item(0);
                    areaValue = areaElement.getTextContent();
                }
                map.put(mt20idValue, areaValue);
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private PerformanceApiDTO detailXmlParsing(String responsebody, HashMap<String, String> performIdList){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responsebody.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.parse(inputStream);

            Element performanceElement = (Element) doc.getElementsByTagName("db").item(0);

            String id = getElementTextContent(performanceElement, "mt20id");
            String name = getElementTextContent(performanceElement, "prfnm");
            String start = getElementTextContent(performanceElement, "prfpdfrom");
            String end = getElementTextContent(performanceElement, "prfpdto");
            String place = getElementTextContent(performanceElement, "fcltynm");
            String time = getElementTextContent(performanceElement, "dtguidance");
            String age = getElementTextContent(performanceElement, "prfage");
            String price = getElementTextContent(performanceElement, "pcseguidance");
            String casting = getElementTextContent(performanceElement, "prfcast");
            String production = getElementTextContent(performanceElement, "entrpsnm");
            String genre = getElementTextContent(performanceElement, "genrenm");
            String performStatus = getElementTextContent(performanceElement, "prfstate");
            String posterUrl = getElementTextContent(performanceElement, "poster");
            String openRun = getElementTextContent(performanceElement, "openrun");
            String area = getElementTextContent(performanceElement, "area");
            if(performIdList != null && area == null){
                area = performIdList.get(id);
            }
            area = areaHandler(id, area);
            Double parsedage = ageHandler(id, age);

            String areacd = null;
            String genrecd = null;
            String regid = "Scheduler";
            LocalDateTime regdt = LocalDateTime.now();

            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);


            return new PerformanceApiDTO(id, name, startDate, endDate, place, time, parsedage, price, casting, production, genre, genrecd, performStatus, posterUrl, openRun, area, areacd, regid, regdt);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //Node의 Null 체크
    private String getElementTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                return node.getTextContent();
            }
        }
        return "";
    }

    private RankApiDto rankXmlParsing(String responsebody, LocalDate rankDate){
        try{
            List<String> performIds = new ArrayList<>();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(responsebody.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.parse(inputStream);

            Element rankElement = (Element) doc.getElementsByTagName("boxofs").item(0);
            for(int i=0; i<10; i++){
                performIds.add(rankElement.getElementsByTagName("mt20id").item(i).getTextContent());
            }
            return new RankApiDto(rankDate, performIds);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String areaHandler(String id, String area){
        String result = "";
        if(area.equals("강원도") || area.equals("강원특별자치도")){
            result = "강원";
        } else if(area.equals("전라북도") || area.equals("전북특별자치도")){
            result = "전북";
        } else if(area.equals("서울특별시")){
            result = "서울";
        } else if(area.equals("부산광역시")){
            result = "부산";
        } else if(area.equals("경기도")){
            result = "경기";
        } else if(area.equals("충청북도")){
            result = "충북";
        } else if(area.equals("제주특별자치도")){
            result = "제주";
        } else if(area.equals("전라남도")){
            result = "전남";
        } else if(area.equals("대구광역시")){
            result = "대구";
        } else if(area.equals("인천광역시")){
            result = "인천";
        } else if(area.equals("광주광역시")){
            result = "광주";
        } else if(area.equals("충청남도")){
            result = "충남";
        } else if(area.equals("대전광역시")){
            result = "대전";
        } else if(area.equals("경상남도")){
            result = "경남";
        } else if(area.equals("울산광역시")){
            result = "울산";
        } else if(area.equals("세종특별자치시")){
            result = "세종";
        } else if(area.equals("경상북도")){
            result = "경북";
        }else{
            System.out.println("[AREA EXCEPTION] " + id + " : " + area);
            result = "기타";
        }

        return result;
    }

    private Double ageHandler(String id, String age){
        double result = 0;
            if (age.matches(".*전체.*")) {
                result = -1;
            } else if (age.matches(".*세.*")) {
                age = age.replaceAll("[^0-9]","");
                result = Double.parseDouble(age);
            } else if (age.matches(".*개월.*") || age.matches(".*게월.*")) {
                age = age.replaceAll("[^0-9]","");
                result = Double.parseDouble(age) / 100;
            } else {
                log.error("[Exception(Age)] " + id + " : " + age);
                result = 0;
            }
        return result;
    }



}
