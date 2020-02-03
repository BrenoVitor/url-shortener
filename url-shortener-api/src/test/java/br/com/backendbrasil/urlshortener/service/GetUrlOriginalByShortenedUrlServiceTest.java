package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.NotFoundUrlByShortAddressAdValidToAfter;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.impl.GetUrlOriginalByShortenedUrlServiceImpl;

public class GetUrlOriginalByShortenedUrlServiceTest {

	private UrlRepository urlRepository;

	private LoggerFacade logger;

	private Url url;

	@BeforeEach
	public void loadUrlRepository() {
		this.urlRepository = mock(UrlRepository.class);
	}

	@BeforeEach
	public void loadLogger() {
		this.logger = mock(LoggerFacade.class);
	}

	@BeforeEach
	public void loadUrl() {
		this.url = new Url("http://www.google.com.br", "abcdef", new Date());
	}

	@Test
	public void getUrlOriginalByShortenedUrlServiceTest() throws NotFoundUrlByShortAddressAdValidToAfter {
		when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.of(this.url));
		GetUrlOriginalByShortenedUrlService getUrlOriginalByShortenedUrlService = new GetUrlOriginalByShortenedUrlServiceImpl(
				this.urlRepository, this.logger);
		Url urlTest = getUrlOriginalByShortenedUrlService.get(new UrlShortenedDto("abcdef"));

		assertEquals(this.url, urlTest);

	}

}
