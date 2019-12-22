package com.threeDays.controller.surface;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassNameSurface
 * @Date2019-12-2116:55
 **/
@RestController
public class Surface {
    @RequestMapping("/index")
    public String login(String name, String pwd, HttpServletRequest request) {
        return "index";
    }
}

