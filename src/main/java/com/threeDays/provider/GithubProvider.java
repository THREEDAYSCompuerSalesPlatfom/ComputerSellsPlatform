package com.threeDays.provider;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.threeDays.POJO.GithubUser;
import com.threeDays.POJO.AccessTokenDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @ClassNameGIthubProvider
 * @Date2019-12-220:58 Component 把当前类初始化当spring容器的上下文
 **/
@Component

public class GithubProvider {
    //post通过access_token,携带code发送 json格式，向远端access_token 接口发送请求，返回获取access_token
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        //对象转string类型json，待测试！！原来方法已失效
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")//post获取access_token
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.   body().string();
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过验证返回的真正的access_token使用，再使用get方法获取用户信息
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//借助fastjson把json转化成对象
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
