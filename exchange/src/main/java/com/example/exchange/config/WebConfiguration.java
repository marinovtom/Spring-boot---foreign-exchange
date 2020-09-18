package com.example.exchange.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;

@Configuration
public class WebConfiguration {
	@Bean
	ServletRegistrationBean h2ServletRegistration()
	{
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		
		return registrationBean;
	}
}
