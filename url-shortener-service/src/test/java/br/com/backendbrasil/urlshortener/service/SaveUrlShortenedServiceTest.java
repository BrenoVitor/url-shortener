package br.com.backendbrasil.urlshortener.service;

import static org.mockito.Matchers.any;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.facade.impl.LoggerFacadeImpl;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.impl.SaveUrlShortenedServiceImpl;

public class SaveUrlShortenedServiceTest {

	@Test
	public void saveUrlShortenedServiceTest() {
		LoggerFacade LOGGER = new LoggerFacadeImpl();
		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
		Mockito.when(urlRepository.save(any(Url.class))).thenReturn(null);
		SaveUrlShortenedService saveUrlShortenedService = new SaveUrlShortenedServiceImpl(urlRepository, LOGGER);
		saveUrlShortenedService.save(new Url("http://www.google.com.br", "abcdef", new Date()));
	}

}
