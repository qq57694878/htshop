package com.ljl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Lijinliang
 * @date 2019/4/28 14:54
 */
public class IdCardCreate {

    private static final int[] idCardWi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final int[]  idCardY = new int[]{1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};


    private static String calculateLast(String card17)throws Exception{
        String last;
        int idCardWiSum = 0; // 用来保存前17位各自乖以加权因子后的总和
        for(int i=0;i<17;i++){
            idCardWiSum+= Integer.parseInt(card17.charAt(i)+"")*idCardWi[i];
        }
        int idCardMod = idCardWiSum % 11; // 计算出校验码所在数组的位置
        if(idCardMod==2){
            last="X";
        }else{
            last=String.valueOf(idCardY[idCardMod]);
        }
        return last;
    }
    public static void main(String[] args) throws Exception{
       String idCard =  createIdCard("19630501","210782",0);
        System.out.println(idCard);
    }
    public static String createIdCard(String birthday,String areaCode,int sex)throws Exception{
        StringBuilder sb = new StringBuilder();
         if(areaCode==null){
             areaCode="210281";
         }
         int last2=random(10);
        if(sex==0){
            last2 = last2%2>0?last2-1:last2;
        }
        sb.append(areaCode).append(birthday).append(random(10)).append(random(10)).append(last2);
        sb.append(calculateLast(sb.toString()));
        return sb.toString();
    }
    public static int random(int len){
         Random random = new Random();
         return random.nextInt(len);
    }
    public static String[][] getAreaCode()throws Exception{
      File file =   ResourceUtils.getFile("classpath:com/ljl/area_code.json");
      InputStream inputStream =  new FileInputStream(file); //IdCardCreate.class.getResourceAsStream("area_code.json");
        JsonParser jsonParser =new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new InputStreamReader(inputStream,"UTF-8"));
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        int len =jsonArray.size();
        String[][] result = new String[len][2];
        for( int i=0;i<len;i++){
            JsonArray p =  jsonArray.get(i).getAsJsonArray();
            result[i]=new String[]{p.get(0).getAsString(),p.get(1).getAsString()};
        }
        return result;
    }
}
