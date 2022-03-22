package com.zzti.school;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;



@SpringBootApplication
@MapperScan(basePackages = "com.zzti.school.mapper")
@EntityScan(basePackages = "com.zzti.school.entity")
public class SchoolApplication {

    /**
      * 解决异常信息：
      *  java.lang.IllegalArgumentException:
      *      Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
      * @return
      */

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
                public void customize(Connector connector) {
                     connector.setProperty("relaxedQueryChars", "|{}[]");
         }
        });
        return factory;
    }


    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);


    }

}
