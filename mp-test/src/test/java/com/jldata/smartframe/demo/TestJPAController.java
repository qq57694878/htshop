package com.jldata.smartframe.demo;

import kulongtai.TestRestBussinessBase;

public class TestJPAController extends TestRestBussinessBase {

   /* @Test
    public void testList() throws IOException {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("page","1");
        param.put("size","10");
        param.put("word","1");
        Response r = RestAssured.given(this.spec).contentType("application/json").header(TOKEN,this.token).body(objectMapper.writeValueAsString(param))
                .post(""+PRE_PATH+"/jpa/list");
        r.then().statusCode(200).body("errcode", Matchers.equalTo(200));
        r.prettyPrint();

    }
    @Test
    public void testListPage() throws IOException {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("page","2");
        param.put("size","10");
        param.put("filter_CONTAINS_title","1");
        param.put("filter_CONTAINS_content","1");
        Response r = RestAssured.given(this.spec).contentType("application/json").header(TOKEN,this.token).body(objectMapper.writeValueAsString(param))
                .post(""+PRE_PATH+"/jpa/listFilter");
        r.then().statusCode(200).body("errcode", Matchers.equalTo(200));
        r.prettyPrint();

    }*/

}
