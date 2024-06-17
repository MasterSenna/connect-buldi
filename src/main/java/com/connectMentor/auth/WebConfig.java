//package com.connectMentor.auth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        // Adicione outros conversores, se necessário
//    }
//    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//            .addResourceHandler("/resources/static/**")
//            .addResourceLocations("classpath:/static/"); // Localização dos recursos estáticos
//    }
//}
