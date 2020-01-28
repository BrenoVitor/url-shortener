package br.com.backendbrasil.urlshortener.service.impl;

import java.util.Date;
import java.util.Optional;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.NotFoundUrlByShortAddressAdValidToAfter;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.GetUrlOriginalByShortenedUrlService;

public class GetUrlOriginalByShortenedUrlServiceImpl implements GetUrlOriginalByShortenedUrlService {

	private static final long serialVersionUID = 884444616917342284L;

	private final UrlRepository urlRepository;

	private final LoggerFacade LOGGER;

	public GetUrlOriginalByShortenedUrlServiceImpl(final UrlRepository urlRepository, final LoggerFacade LOGGER) {
		this.urlRepository = urlRepository;
		this.LOGGER = LOGGER;
	}

	public Url get(UrlShortenedDto urlShortenedDto) throws NotFoundUrlByShortAddressAdValidToAfter {
		LOGGER.info("GetUrlOriginalByShortenedUrlServiceImpl get Begin >>");
		Optional<Url> optionalUrl = urlRepository
				.findByShortAddressAndValidToAfter(urlShortenedDto.getShortenedAddress(), new Date());
		if (!optionalUrl.isPresent()) {
			throw new NotFoundUrlByShortAddressAdValidToAfter("Not found url by short address and valid to after");
		}
		LOGGER.info(String.format("optionalUrl isPresent ==> [ %s ]", optionalUrl.isPresent()));
		LOGGER.info("GetUrlOriginalByShortenedUrlServiceImpl get End <<");
		return optionalUrl.get();
	}

}
