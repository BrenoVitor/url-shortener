package br.com.backendbrasil.urlshortener;

import java.io.Serializable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.facade.impl.LoggerFacadeImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class UrlShortenerApplication implements Serializable {

	private static final long serialVersionUID = -5820727446891970037L;
	
	private static final LoggerFacade LOGGER = new LoggerFacadeImpl();

	public static void main(String[] args) {
		LOGGER.info("UrlShortenerApplication Begin >>");
		SpringApplication.run(UrlShortenerApplication.class, args);
		LOGGER.info("UrlShortenerApplication End <<");

	}
}
