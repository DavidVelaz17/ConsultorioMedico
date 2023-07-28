package com.iwa.iwaid.consultoriomedico.config;

import com.iwa.iwaid.consultoriomedico.convertors.StringToEnumConvertor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/iwaid/doctors")
                .allowedOrigins("3000")
                .allowedMethods("GET","POST", "PUT", "DELETE");
    }
}
