package com.example.SchedulingApp.Developed.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean filterApp() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        //Filter 순서 설정
        filterRegistrationBean.setOrder(1);
        //전체 URL 에 Filter 적용
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterLogin() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        //Filter 순서 설정
        filterRegistrationBean.setOrder(2);
        //전체 URL 에 Filter 적용
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
