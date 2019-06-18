package com.kulongtai.mpstore.common.config;


import com.kulongtai.mpstore.common.exception.ExceptionHandle;
import com.kulongtai.mpstore.common.util.JwtTokenUtil;
import com.kulongtai.mpstore.common.util.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/4/23/023.
 */
@Configuration
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
