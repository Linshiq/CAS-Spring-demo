package com.lsq.lsqweb.util;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    public static String doGet(String url) {

        return null;
    }

    public static String doPost(String url, Map<String, String> paramMap) throws Exception{

        HttpPost httpPost = new HttpPost(url);
        if (!CollectionUtils.isEmpty(paramMap)){
            List<NameValuePair> form = new ArrayList<>();
            paramMap.entrySet().forEach(t -> { form.add(new BasicNameValuePair(t.getKey(),t.getValue()));});

            UrlEncodedFormEntity postInfo = new UrlEncodedFormEntity(form,"utf-8");
            httpPost.setEntity(postInfo);
        }

        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response =  client.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new Exception("调用异常");
        }

        return EntityUtils.toString(response.getEntity(),"utf-8");
    }
}
