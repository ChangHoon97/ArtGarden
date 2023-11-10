package artgarden.server.service;

import artgarden.server.entity.Performance;
import artgarden.server.entity.dto.performanceDto.PerformanceApiDto;
import artgarden.server.repository.KopisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class KopisService {

    private final KopisRepository kopisRepository;


    @Transactional
    public void apiSave() {
        // performId가 모두 저장되는 String 리스트
        List<String> performId = new ArrayList<>();
        //perfomance가 저장되는 performance 리스트
        List<Performance> performances = new ArrayList<>();

        int cpage = 1;
        // 모든 performId가 저장될떄까지 while문 돌아감
        while (true) {
            String responsebody = getPerformId(cpage, "20230601", formatDate());

            List<String> newIdList = idXmlParsing(responsebody);
            if(CollectionUtils.isEmpty(newIdList)){
                break;
            }
            performId.addAll(newIdList);

            cpage++;
        }

        for (String id : performId) {
            Performance performance = new Performance();
            performance.updateFromApiDto(detailXmlParsing(getPerformanceDetail(id)));
            performances.add(performance);
        }

        kopisRepository.saveAll(performances);
    }

    public List<Performance> findAll() {
        return kopisRepository.findAllByOrderByEndDateAsc();
    }

    public Performance findById(String id){

        return kopisRepository.findById(id);
    }

    @Transactional
    public void updatePerformance(){

        // performId가 모두 저장되는 String 리스트
        List<String> performId = new ArrayList<>();
        //perfomance가 저장되는 performance 리스트
        List<Performance> performances = new ArrayList<>();

        int cpage = 1;
        // 모든 performId가 저장될떄까지 while문 돌아감
        while (true) {
            String responsebody = getPerformId(cpage, formatDate(), formatDate());

            List<String> newIdList = idXmlParsing(responsebody);
            if(CollectionUtils.isEmpty(newIdList)){
                break;
            }
            performId.addAll(newIdList);

            cpage++;
        }

        for (String id : performId) {
            Performance performance = new Performance();
            performance.updateFromApiDto(detailXmlParsing(getPerformanceDetail(id)));
            performances.add(performance);
        }

        kopisRepository.saveAll(performances);
    }

    //하루 전 날짜 계산
    private String formatDate(){
        LocalDate currentDate = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return currentDate.format(formatter);
    }

    private String getPerformId(int cpage, String startDate, String endDate) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                .path("/openApi/restful/pblprfr")
                .queryParam("service", "86fdb34b92254e1b84343a5c323e3314")
                .queryParam("stdate", startDate)
                .queryParam("eddate", endDate)
                .queryParam("cpage", cpage)
                .queryParam("rows", 100)
                .encode()
                .build()
                .toUri();

        String url = uri.toString();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }

    private String getPerformanceDetail(String id){
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
                .path("/openApi/restful/pblprfr/{id}")
                .queryParam("service","86fdb34b92254e1b84343a5c323e3314")
                .buildAndExpand(id)
                .toUri();
        String url = uri.toString();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }

    private List<String> idXmlParsing(String responsebody){
        try {
            List<String> performId = new ArrayList<>();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responsebody.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList itemList = root.getElementsByTagName("db");

            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);
                NodeList mt20idNodeList = item.getElementsByTagName("mt20id");

                if (mt20idNodeList.getLength() > 0) {
                    Element mt20idElement = (Element) mt20idNodeList.item(0);
                    String mt20idValue = mt20idElement.getTextContent();
                    performId.add(mt20idValue);
                }
            }
            return performId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private PerformanceApiDto detailXmlParsing(String responsebody){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

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
            String perform_status = performanceElement.getElementsByTagName("prfstate").item(0).getTextContent();
            String posterUrl = performanceElement.getElementsByTagName("poster").item(0).getTextContent();

            Date startDate = format.parse(start);
            Date endDate = format.parse(end);


            return new PerformanceApiDto(id, name, startDate, endDate, place, time, age, price, casting, production, genre, perform_status, posterUrl);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
