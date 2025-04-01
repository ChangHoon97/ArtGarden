package artgarden.server.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrankController {

    @GetMapping("/fake-pay")
    public String showFakePayPage(){
        System.out.println("#########");
        return "fakepay.html";
    }
}
