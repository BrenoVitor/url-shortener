package br.com.backendbrasil.urlshortener.service.impl;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.helper.GeneratorRandomAlphanumericStringHelper;
import br.com.backendbrasil.urlshortener.service.UrlShortenerService;
import br.com.backendbrasil.urlshortener.service.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService;


public class UrlShortenerServiceImpl implements UrlShortenerService {

	private static final long serialVersionUID = 7548532811486013104L;

	private final int minLengthInclusive;

	private final int maxLengthExclusive;

	private final LoggerFacade LOGGER;

	private final GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper;

	private final ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService;

	private final int maxNumberOfRetries;

	public UrlShortenerServiceImpl(final int minLengthInclusive, final int maxLengthExclusive,
			final GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper,
			final LoggerFacade LOGGER,
			final ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService,
			final int maxNumberOfRetries) {
		this.minLengthInclusive = minLengthInclusive;
		this.maxLengthExclusive = maxLengthExclusive;
		this.generatorRandomAlphanumericStringHelper = generatorRandomAlphanumericStringHelper;
		this.LOGGER = LOGGER;
		this.validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = validateUrlShortenerDuplicatedToUrlOriginalAndValidToService;
		this.maxNumberOfRetries = maxNumberOfRetries;
	}

	public final UrlShortenedDto shorten(final UrlOriginalDto urlOriginalDto) throws GenerateUrlShortenedDuplicatedException {
		LOGGER.info("UrlShortenerServiceImpl shorten Begin >>");
		LOGGER.info(String.format("minLengthInclusive ==> [ %s ]", minLengthInclusive));
		LOGGER.info(String.format("maxLengthExclusive ==> [ %s ]", maxLengthExclusive));
		boolean isDuplicated = true;
		UrlShortenedDto urlShortenedDto;
		int retries = 0;
		do {
			urlShortenedDto = new UrlShortenedDto(
					generatorRandomAlphanumericStringHelper.generate(minLengthInclusive, maxLengthExclusive));
			isDuplicated = validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(urlShortenedDto);
			retries++;
		} while (isDuplicated && retries < maxNumberOfRetries);

		if (isDuplicated) {
			throw new GenerateUrlShortenedDuplicatedException(
					"We were unable to generate a shortened, non-duplicate URL");
		}

		LOGGER.info(String.format("urlShortenedDto ==> [ %s ]", urlShortenedDto));
		LOGGER.info("UrlShortenerServiceImpl shorten End <<");
		return urlShortenedDto;
	}

}
