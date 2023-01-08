package com.bankboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class SpringWebMvcConfig implements WebMvcConfigurer {

//    /**
//     * 将本地文件映射到静态路径下，这样网页可以通过url访问本地资源
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("开始静态资源映射");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        registry.addResourceHandler("/images/**").addResourceLocations("file:C:/Users/Lenovo/Pictures/DefaultPath/");
//    }
}
