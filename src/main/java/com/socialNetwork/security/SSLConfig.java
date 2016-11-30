package com.socialNetwork.security;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Class to load a HTTP access
 * 
 * @author UJM's students
 */
@Configuration
public class SSLConfig {

/**
 * Load the http port value from application.yml
 */
@Value("${server.http.port}")
private int httpPort;

/**
 * Bean which use to have the HTTP connection for the social network 
 * 
 * @return ServletContainer
 */
@Bean
public EmbeddedServletContainerCustomizer containerCustomizer() {
    return new EmbeddedServletContainerCustomizer() {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                TomcatEmbeddedServletContainerFactory containerFactory =
                        (TomcatEmbeddedServletContainerFactory) container;

                Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
                connector.setPort(httpPort);
                containerFactory.addAdditionalTomcatConnectors(connector);
            }
        }
    };
}
}
