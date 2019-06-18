package kulongtai.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import kulongtai.TestRestBussinessBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/8 0008.
 */
public class TestOrderController extends TestRestBussinessBase{
    @Test
    public void getTokenHttp(){
        Map<String,Object> body = new HashMap();
        body.put("current",1);
        body.put("size",10);
        body.put("orderStatus","1");
        body.put("userId","1");
        body.put("mobile","13842823735");
        body.put("orderNo","1");
        Response r = RestAssured.given(spec).params(body).get("/api/order/getOrderList");
        r.then().statusCode(200);
        r.prettyPrint();


    }
}
