package org.openhealth.ipf.tutorials.xds.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;

/**
 * @author bovane bovane.ch@gmail.com
 * @date 2024/2/20
 */
@Configuration
public class CamelConfig {
    @Bean
    public CamelContext camelContext() {
        // 初始化并返回CamelContext
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");
        CamelContext camelContext = appContext.getBean(CamelContext.class);
        return camelContext;
    }


//    @Bean
//    public ProducerTemplate producerTemplate(CamelContext camelContext) {
//        // 使用camelContext初始化并返回ProducerTemplate
//        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml");
//        ProducerTemplate producerTemplate = appContext.getBean(ProducerTemplate.class);
//        return producerTemplate;
//    }
}
