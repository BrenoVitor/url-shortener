package br.com.backendbrasil.urlshortener.config;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:business.properties")
public class PropertiesConfig implements Serializable {

	private static final long serialVersionUID = 6976959262063336190L;

}
