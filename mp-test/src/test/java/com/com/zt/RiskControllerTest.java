package com.com.zt;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lijinliang
 * @date 2019/5/9 10:48
 */
public class RiskControllerTest {
    @Before
    public void init() throws Exception {
        //指定 URL 和端口号
        RestAssured.baseURI ="http://10.10.109.52";
        RestAssured.port = 8060;
       RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8").defaultQueryParameterCharset("UTF-8"));
        RestAssured.config = RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultCharsetForContentType("UTF-8","multipart/form-data"));
        RestAssured.config= RestAssured.config().headerConfig(HeaderConfig.headerConfig().mergeHeadersWithName("appId","bn_online"));

    }
@Test
    public void getAppIdControlInfo(){
        Response r = RestAssured.given().header("appId","bn_online_test").contentType("application/json").body("{\"riskCode\":\"5014\"}").post("/riskapi/risk/getappidcontrolinfo");
        r.then().statusCode(200);
        r.prettyPrint();

    }
}
