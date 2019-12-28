package com.threeDays.controller.uaoth2;

import com.threeDays.POJO.AccessTokenDto;
import com.threeDays.POJO.GithubCustomer;
import com.threeDays.POJO.GithubUser;
import com.threeDays.dao.GithubCustomerMapper;
import com.threeDays.provider.GithubProvider;
import com.threeDays.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassNametest
 * @Date2019-12-220:12 GET
 **/

//github登陆：href= https://github.com/login/oauth/authorize?client_id=3d0fc83ea1af95ebd2fd&redirect_uri=http://localhost:8080/callback&scope=user&state=1
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private CustomerService customerService;

    @Value("S{github.client.id}")
    private String clientId;
    @Value("S{github.client.secret}")
    private String clientSecret;
    @Value("S{github.Redirect.url}")
    private String RedirectUrl;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse httpServletResponse) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        //accessTokenDto.setClient_id("3d0fc83ea1af95ebd2fd");
        //accessTokenDto.setClient_secret("a6368413a6c23a164517aae4bad6d9ed846776c3");
        //accessTokenDto.setRedirect_url("http://localhost:8080/callback");
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_url(RedirectUrl);
        accessTokenDto.setState(state);
        githubProvider.getAccessToken(accessTokenDto);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //成功登陆，写入cookie session 维持登陆状态
            GithubCustomer githubCustomer = new GithubCustomer();
            String token = UUID.randomUUID().toString();
            githubCustomer.setToken(token);
            githubCustomer.setAccountId(String.valueOf(githubUser.getId()));
            githubCustomer.setName(githubUser.getName());
            customerService.addGithubCustomer(githubCustomer);
            customerService.mergeCustomer(githubCustomer);//合并生成数据库中customer，返回customerId
            httpServletResponse.addCookie(new Cookie("token", token));//借助token，cookie维持登陆验证是否登陆成功
            //httpServletRequest.getSession().setAttribute("githubUser", githubUser);//th:if="${session.githubUser！=null}"
            return "redirect:/index";
        } else {
            //失败重新登陆
            return "redirect:/index";
        }

    }
}
/*
 * HttpServletRequest httpServletRequest
 * Cookie [] cookies=httpServletRequest.getCookie()
 * for(Cookie cookie:cookies){
 * if(cookie.getName.equal("token")){
 *  String token =cookie.getValue();
 * Customer customer=Customer.findByToken(token)
 * if(customer!=null){
 * httpServletRequest.getSession().setAttribute("customer", customer);
 * }
 *  break;
 * }
 *}
 * */