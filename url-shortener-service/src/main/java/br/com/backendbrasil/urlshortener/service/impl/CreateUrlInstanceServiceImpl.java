package br.com.backendbrasil.urlshortener.service.impl;

import java.util.Calendar;
import java.util.Date;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.helper.SumDateHelper;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.service.CreateUrlInstanceService;

public class CreateUrlInstanceServiceImpl implements CreateUrlInstanceService {

	private static final long serialVersionUID = -1304542057374133421L;

	private final int aditionalDatesForValidTo;

	private final SumDateHelper sumDateHelper;

	private final LoggerFacade LOGGER;

	public CreateUrlInstanceServiceImpl(final int aditionalDatesForValidTo, final SumDateHelper sumDateHelper,
			final LoggerFacade LOGGER) {
		this.aditionalDatesForValidTo = aditionalDatesForValidTo;
		this.sumDateHelper = sumDateHelper;
		this.LOGGER = LOGGER;
	}

	public final Url create(final UrlOriginalDto urlOriginalDto, final UrlShortenedDto urlShortenedDto) {
		LOGGER.info("CreateUrlInstanceServiceImpl create Begin >>");
		LOGGER.info(String.format("urlOriginalDto ==> [ %s ]", urlOriginalDto));
		LOGGER.info(String.format("urlShortenedDto ==> [ %s ]", urlShortenedDto));
		final Date now = new Date();
		LOGGER.info(String.format("now ==> [ %s ]", now));
		final Date validTo = sumDateHelper.sum(now, Calendar.DATE, aditionalDatesForValidTo);
		final Url url = new Url(urlOriginalDto.getAddress(), urlShortenedDto.getShortenedAddress(), validTo);
		LOGGER.info(String.format("url ==> [ %s ]", url));
		LOGGER.info("CreateUrlInstanceServiceImpl create End <<");
		return url;
	}

}
