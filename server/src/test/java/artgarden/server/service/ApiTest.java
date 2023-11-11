//package artgarden.server.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//
//
//@SpringBootTest
//@Transactional
//class ApiTest {
//
//    @Autowired
//    KopisService kopisService;
//
//
//
//    @Test
//    void getPerformId() {
//        URI uri = UriComponentsBuilder.fromUriString("http://www.kopis.or.kr")
//                .path("/openApi/restful/pblprfr")
//                .queryParam("service", "86fdb34b92254e1b84343a5c323e3314")
//                .queryParam("stdate", 20160101)
//                .queryParam("eddate", 20170101)
//                .queryParam("cpage", 1)
//                .queryParam("rows", 10).encode().build().toUri();
//        String url = uri.toString();
//        System.out.println(uri.toString());
//        ResponseEntity<String> res = new RestTemplate().getForEntity(url, String.class);
//
//        System.out.println(res.getBody());
//        System.out.println(res.getStatusCode());
//    }
//
//    @Test
//    void serviceTest(){
//        kopisService.apiSave();
//    }
//
//}