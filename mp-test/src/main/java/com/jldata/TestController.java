package com.jldata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Lijinliang
 * @date 2019/4/25 16:30
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
   @PostMapping("hi")
    public String hi(@RequestBody Map<String,String> body)throws Exception{
       String s  = restTemplate.postForObject("http://localhost:8080/hello",body,String.class);
       /*ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(body);*/
       return s;
    }
    @PostMapping("hello")
    public String hello(@RequestBody Map<String,String> body)throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(body);
    }
}
