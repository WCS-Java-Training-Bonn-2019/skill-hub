package com.wildcodeschool.skillhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.skillhub.Converter.MultipartFileToByteArrayConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(new MultipartFileToByteArrayConverter());
	}
	
}
