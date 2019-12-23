package com.threeDays.controller.uaoth2;



import com.threeDays.POJO.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/test")
    public Customer sayHello(){
        System.out.println("==================> augular");
        Customer user = new Customer();
        user.setCu_Name("jackson");
        user.setCu_Telephone("123456");
        return user;
    }
}

