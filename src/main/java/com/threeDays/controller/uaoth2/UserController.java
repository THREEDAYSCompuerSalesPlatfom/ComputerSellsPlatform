package com.threeDays.controller.uaoth2;



import com.threeDays.POJO.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @GetMapping("/testss")
    @ResponseBody
    public Customer sayHello(){
        System.out.println("==================> augular");
        Customer user = new Customer();
        user.setCu_Name("jackson");
        user.setCu_Telephone("123456");
        return user;
    }
}

