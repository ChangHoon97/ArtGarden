package artgarden.server.culture.service;

import artgarden.server.exhibit.entity.Exhibit;
import artgarden.server.exhibit.entity.dto.ExhibitApiDTO;
import artgarden.server.exhibit.repository.ExhibitRepository;
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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CultureServiceImpl implements CultureService{

    private final ExhibitRepository exhibitRepository;

    @Transactional
    public void updateExhibitList(String exhibitType) throws Exception{
        List<Exhibit> exhibitList = getExhibitList(exhibitType);
        exhibitRepository.saveAll(exhibitList);
    }

    private List<Exhibit> getExhibitList(String exhibitType) throws Exception {
        List<Exhibit> exhibitList = new ArrayList<>();
        int cpage = 1;
        String serviceKey = "KSM9CQi7MsuWXEDgfcqxIcqnB4XnZB1AAZQ6xlG6FrWbWt5Sc5HaRxNGVV%2BWHZrgaEssnCCJVWLJcbT78%2B3qPA%3D%3D";
        while(true) {
            /*RestTemplate restTemplate = new RestTemplate();
            String url2 = "http://www.culture.go.kr/openapi/rest/publicperformancedisplays/realm?serviceKey=KSM9CQi7MsuWXEDgfcqxIcqnB4XnZB1AAZQ6xlG6FrWbWt5Sc5HaRxNGVV%2BWHZrgaEssnCCJVWLJcbT78%2B3qPA%3D%3D&sortStdr=1&cPage=" + cpage + "&rows=1000&realmCode="+ exhibitType;
            URI uri = UriComponentsBuilder.fromUriString("http://www.culture.go.kr")
                    .path("/openapi/rest/publicperformancedisplays/realm")
                    .queryParam("serviceKey", serviceKey) // serviceKey를 인코딩된 상태로 전달
                    .queryParam("sortStdr", "1")
                    .queryParam("cpage", cpage)
                    .queryParam("rows", 1000)
                    .queryParam("realmCode", exhibitType)
                    .build()
                    .toUri();

            String url = uri.toString();
            String responsebody = restTemplate.getForEntity(url, String.class).getBody();
            //xnmlParsing*/

            StringBuilder urlBuilder = new StringBuilder("http://www.culture.go.kr/openapi/rest/publicperformancedisplays/realm"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("sortStdr","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*1:등록일, 2:공연명, 3:지역*/
            urlBuilder.append("&" + URLEncoder.encode("realmCode","UTF-8") + "=" + URLEncoder.encode(exhibitType, "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("cPage","UTF-8") + "=" + URLEncoder.encode(Integer.toString(cpage), "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("rows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*3~100*/
            urlBuilder.append("&" + URLEncoder.encode("rows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*3~100*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            List<Exhibit> newExhibitList = exhibitXmlParsing(sb.toString());
            //endPoint check
            if (CollectionUtils.isEmpty(newExhibitList)) {
                break;
            }

            exhibitList.addAll(newExhibitList);

            cpage++;
        }

        return exhibitList;
    }

    private List<Exhibit> exhibitXmlParsing(String responsebody) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            List<Exhibit> exhibitList = new ArrayList<>();
            ExhibitApiDTO dto = new ExhibitApiDTO();
            dto.setRegdt(LocalDateTime.now());
            dto.setRegid("Scheduler");

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responsebody.getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            Document doc = build.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList itemList = root.getElementsByTagName("perforList");

            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);
                dto.setId(item.getElementsByTagName("seq").item(0).getTextContent());
                dto.setName(item.getElementsByTagName("title").item(0).getTextContent());
                dto.setStartdate(LocalDate.parse(item.getElementsByTagName("startDate").item(0).getTextContent(),formatter));
                dto.setEnddate(LocalDate.parse(item.getElementsByTagName("endDate").item(0).getTextContent(), formatter));
                dto.setPlace(item.getElementsByTagName("place").item(0).getTextContent());
                dto.setArea(item.getElementsByTagName("area").item(0).getTextContent());
                dto.setPosterurl(item.getElementsByTagName("thumbnail").item(0).getTextContent());
                dto.setGenre("미술");
                Exhibit vo = new Exhibit();
                vo.updateFromApiDto(dto);
                exhibitList.add(vo);

            }
            return exhibitList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void updateEXStatus(){
        exhibitRepository.updateCode();
    }
}
