package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.helper.SumDateHelper;
import br.com.backendbrasil.urlshortener.helper.impl.SumDateHelperImpl;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.service.impl.CreateUrlInstanceServiceImpl;

public class CreateUrlInstanceServiceTest {

	private UrlOriginalDto urlOriginalDto;
	private UrlShortenedDto urlShortenedDto;
	private LoggerFacade logger;
	private Date expectedDate;

	@BeforeEach
	public void loadUrlOriginalDto() {
		this.urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
	}

	@BeforeEach
	public void loadUrlShortenedDto() {
		this.urlShortenedDto = new UrlShortenedDto("abdcef");
	}

	@BeforeEach
	public void loadLogger() {
		this.logger = mock(LoggerFacade.class);
	}

	@BeforeEach
	public void loadDate() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		this.expectedDate = calendar.getTime();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void createUrlInstanceServiceTest() {
		SumDateHelper sumDateHelper = new SumDateHelperImpl();
		CreateUrlInstanceService createUrlInstanceService = new CreateUrlInstanceServiceImpl(1, sumDateHelper,
				this.logger);
		Url url = createUrlInstanceService.create(urlOriginalDto, urlShortenedDto);
		assertEquals("http://www.google.com.br", url.getOriginAddress());
		assertEquals("abdcef", url.getShortAddress());
		assertEquals(this.expectedDate.getDate(), url.getValidTo().getDate());
	}

}
