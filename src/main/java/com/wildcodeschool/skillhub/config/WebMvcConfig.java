package com.wildcodeschool.skillhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.skillhub.Converter.MultipartFileToByteArrayConverter;


//@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
	}
	
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(new MultipartFileToByteArrayConverter());
	}
	
}
