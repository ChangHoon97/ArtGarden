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
        System.out.println("가져오는 날짜 : " + rankDate);
        System.out.println(LocalDateTime.now());
        WeeklyRank weeklyRank = getRank(ststype, rankDate);
        WeeklyRank checkWeeklyRank = rankRepository.findByRankDate(weeklyRank.getRankDate());
        if(checkWeeklyRank != null){
            checkWeeklyRank.updateFromRank(weeklyRank);
            rankRepository.save(checkWeeklyRank);
        }else{
            rankRepository.save(weeklyRank);
        }
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
            List<String> performId = new ArrayList<>();
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

                if (mt20idNodeList.getLength() > 0) {
                    Element mt20idElement = (Element) mt20idNodeList.item(0);
                    Element areaElement = (Element) areaNodeList.item(0);
                    String mt20idValue = mt20idElement.getTextContent();
                    String areaValue = areaElement.getTextContent();
                    performId.add(mt20idValue);
                    map.put(mt20idValue, areaValue);
                }
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

            String id = performanceElement.getElementsByTagName("mt20id").item(0).getTextContent();
            String name = performanceElement.getElementsByTagName("prfnm").item(0).getTextContent();
            String start = performanceElement.getElementsByTagName("prfpdfrom").item(0).getTextContent();
            String end = performanceElement.getElementsByTagName("prfpdto").item(0).getTextContent();
            String place = performanceElement.getElementsByTagName("fcltynm").item(0).getTextContent();
            String time = performanceElement.getElementsByTagName("dtguidance").item(0).getTextContent();
            String age = performanceElement.getElementsByTagName("prfage").item(0).getTextContent();
            String price = performanceElement.getElementsByTagName("pcseguidance").item(0).getTextContent();
            String casting = performanceElement.getElementsByTagName("prfcast").item(0).getTextContent();
            String production = performanceElement.getElementsByTagName("entrpsnm").item(0).getTextContent();
            String genre = performanceElement.getElementsByTagName("genrenm").item(0).getTextContent();
            String performStatus = performanceElement.getElementsByTagName("prfstate").item(0).getTextContent();
            String posterUrl = performanceElement.getElementsByTagName("poster").item(0).getTextContent();
            String openRun = performanceElement.getElementsByTagName("openrun").item(0).getTextContent();
            String area = performanceElement.getElementsByTagName("area").item(0).getTextContent();
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
        if(area.equals("강원도")){
            result = "강원특별자치도";
        } else if(area.equals("전라북도")){
            result = "전북특별자치도";
        } else{
            if(area.isEmpty()){
                log.error("[Exception(Area)] " + id + " / " + area);
            }
            result = area;
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
