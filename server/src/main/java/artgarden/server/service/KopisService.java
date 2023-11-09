package artgarden.server.service;

import artgarden.server.entity.Perforamnce;
import artgarden.server.repository.KopisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KopisService {

    private KopisRepository kopisRepository;

    @Transactional
    public void save(Perforamnce perforamnce){

        kopisRepository.save(perforamnce);
    }

    public void findAll(){
        kopisRepository.findAllByOrderByEndDateAsc();
    }

    private void getPerformanceDetail(){
        String performId="";
        try{
            String apiUrl = "http://www.kopis.or.kr/openApi/restful/pblprfr/";


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
