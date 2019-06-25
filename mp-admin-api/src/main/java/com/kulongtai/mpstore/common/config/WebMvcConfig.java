package com.kulongtai.mpstore.common.config;

import com.kulongtai.mpstore.common.intercepter.UserAuthRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {
   /* private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);//这两句不加不能跨域上传文件，
        corsConfiguration.setMaxAge(3600l);//加上去就可以了
        return corsConfiguration;
    }

    *//**
     * 跨域过滤器
     * @return
     *//*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*//**", buildConfig()); // 4
        return new CorsFilter(source);
    }*/
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
               // registry.addConverter( new DateConverter());
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders()
                        .allowCredentials(false).maxAge(3600);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(getUserAuthRestInterceptor()).
                        addPathPatterns("/**/*")
                        .excludePathPatterns("/**/fonts/*")
                        .excludePathPatterns("/**/*.css")
                        .excludePathPatterns("/**/*.js")
                        .excludePathPatterns("/**/*.png")
                        .excludePathPatterns("/**/*.gif")
                        .excludePathPatterns("/**/*.jpg")
                        .excludePathPatterns("/**/*.jpeg")
                        .excludePathPatterns("/**/*.html")
                        .excludePathPatterns("/static/*")
                        .excludePathPatterns("/public/*")
                        .excludePathPatterns("/webjars/*")
                        .excludePathPatterns("/api/public/*")
                        .excludePathPatterns("/api/media/*")
                        .excludePathPatterns("/api/login");
            }

        };
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

}
