package artgarden.server.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrankController {

    @GetMapping("/breakingNews")
    public String showFakePayPage(){
        return "fakepay.html";
    }

    @GetMapping("/mbsNews")
    public String showFakePayPage2(){
        return "mbs.html";
    }

    @GetMapping("/americeNews")
    public String showFakePayPage3(){
        return "americe.html";
    }

    @GetMapping("/americsNews")
    public String showFakePayPage4(){
        return "americs.html";
    }
}
