package com.jeesite.modules.love.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jeesite.modules.love.interceptor.OAuth2Interceptor;
import com.jeesite.modules.love.interceptor.AppLoginInterceptor;

@Configuration
public class AppConfigAdapter implements WebMvcConfigurer{

	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addInterceptor(OAuth2Interceptor()).addPathPatterns("app/**");
//		super.addInterceptors(registry);
//	}
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addResourceHandler("/app/**").addResourceLocations("classpath:/static/");
//		super.addResourceHandlers(registry);
//	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(AppLoginInterceptor()).addPathPatterns("/app");
		registry.addInterceptor(OAuth2Interceptor()).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
    @Bean
    public OAuth2Interceptor OAuth2Interceptor() {
        return new OAuth2Interceptor();
    }
    
    @Bean
    public AppLoginInterceptor AppLoginInterceptor() {
        return new AppLoginInterceptor();
    }
    
    
}
