package br.com.backendbrasil.urlshortener.validator.impl;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.service.ValidateUrlOriginalService;
import br.com.backendbrasil.urlshortener.validator.AbstractValidator;

public class UrlOriginalValidatorImpl extends AbstractValidator<UrlOriginalDto> {

	private static final long serialVersionUID = -7190340794430285775L;

	private ValidateUrlOriginalService validateUrlOriginalService;

	private final LoggerFacade LOGGER;

	private static final String FIELD_NAME = "address";

	public UrlOriginalValidatorImpl(ValidateUrlOriginalService validateUrlOriginalService, final LoggerFacade LOGGER) {
		super(UrlOriginalDto.class);
		this.validateUrlOriginalService = validateUrlOriginalService;
		this.LOGGER = LOGGER;
	}

	@Override
	public String getFieldName() {
		LOGGER.info("UrlOriginalValidatorImpl getFieldName Begin >>");
		LOGGER.info(String.format("FIELD_NAME ==> [ %s ]", FIELD_NAME));
		LOGGER.info("UrlOriginalValidatorImpl getFieldName End <<");
		return FIELD_NAME;
	}

	@Override
	public boolean isValid(UrlOriginalDto object) {
		LOGGER.info("UrlOriginalValidatorImpl isValid Begin >>");
		boolean isValid = validateUrlOriginalService.isValid(object);
		LOGGER.info(String.format("isValid ==> [ %s ]", isValid));
		LOGGER.info("UrlOriginalValidatorImpl isValid End <<");
		return isValid;

	}

}
