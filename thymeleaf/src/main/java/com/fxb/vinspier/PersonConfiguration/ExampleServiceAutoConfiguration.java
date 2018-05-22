package com.fxb.vinspier.PersonConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ExampleServiceProperties.class)
@ConditionalOnClass(ExampleService.class)
@ConditionalOnProperty(prefix = "example",value = "enabled",matchIfMissing = true)
public class ExampleServiceAutoConfiguration {
    @Autowired
    private ExampleServiceProperties exampleServiceProperties;

    @Bean
    @ConditionalOnMissingBean(ExampleService.class)
    public ExampleService exampleService(){
        ExampleService exampleService = new ExampleService();
        exampleService.setMsg(exampleServiceProperties.getMsg());
        return exampleService;
    }
}
