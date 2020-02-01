package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.facade.impl.LoggerFacadeImpl;
import br.com.backendbrasil.urlshortener.helper.SumDateHelper;
import br.com.backendbrasil.urlshortener.helper.impl.SumDateHelperImpl;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.service.impl.CreateUrlInstanceServiceImpl;

public class CreateUrlInstanceServiceTest {

	@SuppressWarnings("deprecation")
	@Test
	public void createUrlInstanceServiceTest() {
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
		UrlShortenedDto urlShortenedDto = new UrlShortenedDto("abdcef");
		SumDateHelper sumDateHelper = new SumDateHelperImpl();
		LoggerFacade LOGGER = new LoggerFacadeImpl();
		CreateUrlInstanceService createUrlInstanceService = new CreateUrlInstanceServiceImpl(1, sumDateHelper, LOGGER);
		Url url = createUrlInstanceService.create(urlOriginalDto, urlShortenedDto);
		assertEquals("http://www.google.com.br", url.getOriginAddress());
		assertEquals("abdcef", url.getShortAddress());
		assertEquals(new Date(2020,1,01).getDate(), url.getValidTo().getDate());
	}

}
