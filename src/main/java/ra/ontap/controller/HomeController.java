package ra.ontap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home") // khai báo đường dẫn xử lí
    public String home(){
       return "home" ; // chọn tên view để trả về
    }
    @RequestMapping
    public String index(){
        return "index";
    }
}
