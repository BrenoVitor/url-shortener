package br.com.backendbrasil.urlshortener.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.facade.impl.LoggerFacadeImpl;
import br.com.backendbrasil.urlshortener.helper.impl.GeneratorRandomAlphanumericStringHelperImpl;
import br.com.backendbrasil.urlshortener.helper.impl.SumDateHelperImpl;
import br.com.backendbrasil.urlshortener.helper.impl.UrlValidatorHelperImpl;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.CreateUrlInstanceService;
import br.com.backendbrasil.urlshortener.service.GetUrlOriginalByShortenedUrlService;
import br.com.backendbrasil.urlshortener.service.SaveUrlShortenedService;
import br.com.backendbrasil.urlshortener.service.UrlShortenerService;
import br.com.backendbrasil.urlshortener.service.impl.CreateUrlInstanceServiceImpl;
import br.com.backendbrasil.urlshortener.service.impl.GetUrlOriginalByShortenedUrlServiceImpl;
import br.com.backendbrasil.urlshortener.service.impl.SaveUrlShortenedServiceImpl;
import br.com.backendbrasil.urlshortener.service.impl.UrlShortenerServiceImpl;
import br.com.backendbrasil.urlshortener.service.impl.ValidateUrlOriginalServiceImpl;
import br.com.backendbrasil.urlshortener.service.impl.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl;
import br.com.backendbrasil.urlshortener.validator.CommonValidator;
import br.com.backendbrasil.urlshortener.validator.impl.UrlOriginalValidatorImpl;

@Configuration
public class BeanConfig implements Serializable {

	private static final long serialVersionUID = -914110269122601489L;

	private static final LoggerFacade LOGGER = new LoggerFacadeImpl();

	@Value("${min.length.inclusive}")
	private String minLengthInclusive;

	@Value("${max.length.exclusive}")
	private String maxLengthExclusive;

	@Value("${valid.to.aditional.days}")
	private String validToAditionalDays;

	@Value("${max.number.of.retries}")
	private String maxNumberOfRetries;

	@Autowired
	private UrlRepository urlRepository;

	@Bean(value = "UrlShortenerService")
	public UrlShortenerService urlShortenerService() {
		LOGGER.info("UrlShortenerService injection UrlShortenerServiceImpl Begin >>");
		LOGGER.info(String.format("minLengthInclusive ==> [ %s ]", minLengthInclusive));
		LOGGER.info(String.format("maxLengthExclusive ==> [ %s ]", maxLengthExclusive));
		LOGGER.info("GeneratorRandomAlphanumericStringHelper ==> [ GeneratorRandomAlphanumericStringHelperImpl ]");
		UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl(Integer.parseInt(minLengthInclusive),
				Integer.parseInt(maxLengthExclusive), new GeneratorRandomAlphanumericStringHelperImpl(), LOGGER,
				new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(urlRepository),
				Integer.parseInt(maxNumberOfRetries));
		LOGGER.info("UrlShortenerService injection UrlShortenerServiceImpl End <<");
		return urlShortenerService;

	}

	@Bean(value = "GetUrlOriginalByShortenedUrlService")
	public GetUrlOriginalByShortenedUrlService getUrlOriginalByShortenedUrlService() {
		LOGGER.info("GetUrlOriginalByShortenedUrlService injection GetUrlOriginalByShortenedUrlServiceImpl Begin >>");
		LOGGER.info("urlRepository ==> [ urlRepository ]");
		GetUrlOriginalByShortenedUrlService getUrlOriginalByShortenedUrlService = new GetUrlOriginalByShortenedUrlServiceImpl(
				urlRepository, LOGGER);
		LOGGER.info("GetUrlOriginalByShortenedUrlService injection GetUrlOriginalByShortenedUrlServiceImpl End <<");
		return getUrlOriginalByShortenedUrlService;
	}

	@Bean(value = "SaveUrlShortenedService")
	public SaveUrlShortenedService saveUrlShortenedService() {
		LOGGER.info("SaveUrlShortenedService injection SaveUrlShortenedServiceImpl Begin >>");
		LOGGER.info("urlRepository ==> [ urlRepository ]");
		final SaveUrlShortenedService saveUrlShortenedService = new SaveUrlShortenedServiceImpl(urlRepository, LOGGER);
		LOGGER.info("SaveUrlShortenedService injection SaveUrlShortenedServiceImpl End >>");
		return saveUrlShortenedService;
	}

	@Bean(value = "CreateUrlInstanceService")
	public CreateUrlInstanceService createUrlInstanceService() {
		LOGGER.info("CreateUrlInstanceService injection CreateUrlInstanceServiceImpl Begin >>");
		LOGGER.info(String.format("validToAditionalDays ==> [ %s ]", validToAditionalDays));
		LOGGER.info("SumDateHelperImpl ==> [ SumDateHelperImpl ]");
		CreateUrlInstanceService createUrlInstanceService = new CreateUrlInstanceServiceImpl(
				Integer.parseInt(validToAditionalDays), new SumDateHelperImpl(), LOGGER);
		LOGGER.info("CreateUrlInstanceService injection CreateUrlInstanceServiceImpl End <<");
		return createUrlInstanceService;

	}

	@Bean(value = "CommonValidator")
	public CommonValidator<UrlOriginalDto> commonValidator() {
		LOGGER.info("CommonValidator injection ValidateUrlOriginalServiceImpl Begin >>");
		LOGGER.info("ValidateUrlOriginalServiceImpl ==> [ ValidateUrlOriginalServiceImpl ]");
		LOGGER.info("UrlValidatorHelperImpl ==> [ UrlValidatorHelperImpl ]");
		CommonValidator<UrlOriginalDto> urlOriginalValidator = new UrlOriginalValidatorImpl(
				new ValidateUrlOriginalServiceImpl(new UrlValidatorHelperImpl()), LOGGER);
		LOGGER.info("CommonValidator injection ValidateUrlOriginalServiceImpl End <<");
		return urlOriginalValidator;
	}

	@Bean(value = "LoggerFacade")
	public LoggerFacade loggerFacade() {
		return new LoggerFacadeImpl();
	}

}
