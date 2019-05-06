package com.lenily.dreamcustomer.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author: success
 * @create: 2019-05-06 11:39:05
 **/
public class PictureUtil {

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            // 用java JDK自带的URL去请求
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置该请求的消息头
            // 设置HTTP方法：POST
            connection.setRequestMethod("POST");
            // 设置其Header的Content-Type参数为application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "VDq1GemzIMGGvcNHWdGrFtpt");
            // 将第二步获取到的token填入到HTTP header
            connection.setRequestProperty("access_token", baiduOcr.getAuth());
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 把json格式转换成HashMap
    public static HashMap<String, String> getHashMapByJson(String jsonResult) {
        HashMap map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonResult);
            JSONArray words_result1 = jsonObject.getJSONArray("words_result");
            Iterator<String> it = words_result1.iterator();
            while (it.hasNext()) {
                JSONObject wordsResult = JSONObject.fromObject(it.next());
                System.out.println(wordsResult.get("words"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        // 获取本地的绝对路径图片
        File file = new File("/Users/success/Downloads/Port.jpeg");
        // 进行BASE64位编码
        String imageBase = BASE64.encodeImgageToBase64(file);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        // 百度云的文字识别接口,后面参数为获取到的token
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=" + baiduOcr.getAuth();
        String httpArg = "detect_direction=true&id_card_side=front&image=" + imageBase;
        String jsonResult = request(httpUrl, httpArg);
        System.out.println("返回的结果--------->" + jsonResult);
        HashMap<String, String> map = getHashMapByJson(jsonResult);
    }

}