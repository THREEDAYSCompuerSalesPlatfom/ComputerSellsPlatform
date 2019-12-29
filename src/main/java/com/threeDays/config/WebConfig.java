package com.threeDays.config;

import com.threeDays.Interceptor.SessionInterceptor;
import com.threeDays.Interceptor.sellerssessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassNameWebConfig
 * @Date2019-12-2119:08 注册 拦截器
 **/
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Autowired
    private sellerssessionInterceptor sellerssessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径：必须登录才可以访问
        List<String> patterns = new ArrayList<>();
        patterns.add("/bigGoods");
        patterns.add("/directBuy");
        patterns.add("/buyByCart");
        patterns.add("/addToCart");
        patterns.add("/getCart");
        patterns.add("/updateLittleGoods");
        patterns.add("/deleteLittleGoods");
        patterns.add("/changePassword");
        patterns.add("/littlerGoods");

        // 白名单：在黑名单范围内，却不需要登录就可以访问
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/account");
        excludePatterns.add("/gitLogin");
        excludePatterns.add("/signIn");
        excludePatterns.add("/signUp");
        excludePatterns.add("/index");
        excludePatterns.add("/captcha");
        excludePatterns.add("/image/getIxdexImage");
        excludePatterns.add("/image/AllImageNames");
        excludePatterns.add("/image/get");
        excludePatterns.add("/searchGoods");
        excludePatterns.add("/callback");
        excludePatterns.add("/bigGoods");
//        excludePatterns.add("/selogin");
//        excludePatterns.add("/selogin/submit");
//        excludePatterns.add("/sellerregesiter");
//        excludePatterns.add("/sellerregesiter/submit");
//        excludePatterns.add("/sellerregesiter/isNameExist");
        // 注册拦截器
      registry .addInterceptor(sessionInterceptor).addPathPatterns(patterns).excludePathPatterns(excludePatterns);

        List<String> sellerpatterns = new ArrayList<>();
        sellerpatterns.add("/AfterSales");
        sellerpatterns.add("/AfterSales/findALLAftersales");
        sellerpatterns.add("/AfterSales/changeAfterSalesStatus");
        sellerpatterns.add("/AfterSales/updateSellerExcuse");
        sellerpatterns.add("/DeliverManage");
        sellerpatterns.add("/DeliverManage/findOrderByStatus");
        sellerpatterns.add("/DeliverManage/findDeliverById");
        sellerpatterns.add("/DeliverManage/insertExpress");
        sellerpatterns.add("/DeliverManage/updateExpress");
        sellerpatterns.add("/DeliverManage/deleteExpress");
        sellerpatterns.add("//DeliverManage/insertReply");
        sellerpatterns.add("/DeliverManage/findGoodsIdByOrderId");
        sellerpatterns.add("/DeliverManage/findGoodsNumByOrderId");
        sellerpatterns.add("/DeliverManage/queryDelivery");
        sellerpatterns.add("/GoodsManage");
        sellerpatterns.add("//GoodsManage/getPAGEBigGoodsBySellerId");
        sellerpatterns.add("/GoodsManage/updateBigGoods");
        sellerpatterns.add("/GoodsManage/deleteBigGoods");
        sellerpatterns.add("/GoodsManage/addNewLittleGoods");
        sellerpatterns.add("/GoodsManage/findLittleGoods");
        sellerpatterns.add("/GoodsManage/deleteLittleGoodsById");
        sellerpatterns.add("/GoodsManage/updateLittleGoods");
        sellerpatterns.add("/GoodsManage/minPrice");
        sellerpatterns.add("/GoodsManage/maxPrice");
        sellerpatterns.add("/GoodsManage/updateLittleGoodsPrice");
        sellerpatterns.add("/ReturnSalesManage/findOrderByStatus");
        sellerpatterns.add("/ReturnSalesManage/changeStatus");
        sellerpatterns.add("/querySellerInfo");
        sellerpatterns.add("/updateSellerInfo");
        sellerpatterns.add("/image/uploadindex");
        sellerpatterns.add("/image/upload");
        sellerpatterns.add("/image/deleteImage");
        sellerpatterns.add("/updateSellerInfo");
        sellerpatterns.add("/deliver");
        sellerpatterns.add("/comment");
        sellerpatterns.add("/backgoods");
        sellerpatterns.add("/newgoods");
        sellerpatterns.add("/goods");
        sellerpatterns.add("/backgoods");
        sellerpatterns.add("/sellermanage");
        sellerpatterns.add("/home");
        sellerpatterns.add("/sellerinfo");
        sellerpatterns.add("/password");

        List<String> sellerexcludePatterns = new ArrayList<>();
        sellerexcludePatterns.add("/captcha");
        sellerexcludePatterns.add("/selogin");
        sellerexcludePatterns.add("/selogin/submit");
        sellerexcludePatterns.add("/sellerregesiter");
        sellerexcludePatterns.add("/sellerregesiter/submit");
        sellerexcludePatterns.add("/sellerregesiter/isNameExist");
        //registry.addInterceptor(sellerssessionInterceptor).excludePathPatterns(sellerexcludePatterns).addPathPatterns(sellerpatterns);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }
}
