package com.github.northfox.web.mempro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Profile("container")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  private static final Logger logger =
      LoggerFactory.getLogger(WebMvcConfig.class);
  
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    logger.info("Do configureContentNegotiation,");
    
    configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
  }
}