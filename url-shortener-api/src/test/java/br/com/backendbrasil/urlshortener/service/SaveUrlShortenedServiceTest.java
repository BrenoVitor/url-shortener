package br.com.backendbrasil.urlshortener.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.impl.SaveUrlShortenedServiceImpl;

public class SaveUrlShortenedServiceTest {

	private Url url;

	private UrlRepository urlRepository;
	
	private LoggerFacade logger;

	@BeforeEach
	public void loadUrl() {
		this.url = new Url("http://www.google.com.br", "abcdef", new Date());
	}

	@BeforeEach
	public void loadUrlRepository() {
		this.urlRepository = mock(UrlRepository.class);
	}
	
	@BeforeEach
	public void loadUrlLogger() {
		this.logger = mock(LoggerFacade.class);
	}

	@Test
	public void saveUrlShortenedServiceTest() {
//		Mockito.when(urlRepository.save(any(Url.class))).thenReturn(null);
		SaveUrlShortenedService saveUrlShortenedService = new SaveUrlShortenedServiceImpl(this.urlRepository, this.logger);
		saveUrlShortenedService.save(this.url);
		verify(this.urlRepository, times(1)).save(this.url);

	}

}
