package br.com.backendbrasil.urlshortener.cucumber.configuration;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import br.com.backendbrasil.urlshortener.UrlShortenerApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = UrlShortenerApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {

}
