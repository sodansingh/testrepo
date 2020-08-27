package com.task.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSFilter implements WebMvcConfigurer {

	//configurations class for allowing origins and methods 
 @Override
 public void addCorsMappings(CorsRegistry registry) {
  registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT","DELETE");

 }
} 
