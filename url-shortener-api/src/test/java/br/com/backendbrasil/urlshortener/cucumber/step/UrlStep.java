package br.com.backendbrasil.urlshortener.cucumber.step;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.helper.GeneratorRandomAlphanumericStringHelper;
import br.com.backendbrasil.urlshortener.helper.impl.GeneratorRandomAlphanumericStringHelperImpl;
import br.com.backendbrasil.urlshortener.service.UrlShortenerService;
import br.com.backendbrasil.urlshortener.service.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService;
import br.com.backendbrasil.urlshortener.service.impl.UrlShortenerServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UrlStep {
	
	private UrlOriginalDto urlOriginalDto;
	private UrlShortenedDto urlShortenedDto;
	private LoggerFacade logger;
	private ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService;

	@Given("^an URL Valid \"(.*?)\"$")
	public void createAValidUrl(String urlValid) {
		this.logger = mock(LoggerFacade.class);
		this.validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = Mockito
				.mock(ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService.class);
		this.urlOriginalDto = new UrlOriginalDto(urlValid);
	}

	@When("^this URL is send for the system$")
	public void sendAValidUrlForTheSystem() throws GenerateUrlShortenedDuplicatedException {
		GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper = new GeneratorRandomAlphanumericStringHelperImpl();
		UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl(5, 10,
				generatorRandomAlphanumericStringHelper, logger,
				validateUrlShortenerDuplicatedToUrlOriginalAndValidToService, 3);
		this.urlShortenedDto = urlShortenerService.shorten(this.urlOriginalDto);
	}
	
	@Then("^is returned an shortened URL$")
	public void returnShortenedUrl() {
		assertTrue(urlShortenedDto.getShortenedAddress().length() > 5);
		assertTrue(urlShortenedDto.getShortenedAddress().length() < 10);
	}

}
