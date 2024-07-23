package com.ensah.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(getAllowedOrigins())
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }

    private String[] getAllowedOrigins() {
        String[] allowedOrigins = new String[21];
        for (int i = 0; i <= 20; i++) {
            allowedOrigins[i] = "http://localhost:" + (3000 + i);
        }
        return allowedOrigins;
    }

}
