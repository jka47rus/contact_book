package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties(){
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yaml"));
//        configurer.setProperties(yamlPropertiesFactoryBean.getObject());
//        return configurer;
//    }

//   public Interface interfaceMethod(){
//       return new Interface();
//   }
}
