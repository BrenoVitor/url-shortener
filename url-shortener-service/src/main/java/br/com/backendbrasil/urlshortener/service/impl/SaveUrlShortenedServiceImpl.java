package br.com.backendbrasil.urlshortener.service.impl;

import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.SaveUrlShortenedService;


public class SaveUrlShortenedServiceImpl implements SaveUrlShortenedService {

	private static final long serialVersionUID = -833771448049127455L;

	private final UrlRepository urlRepository;

	private final LoggerFacade LOGGER;

	public SaveUrlShortenedServiceImpl(final UrlRepository urlRepository, final LoggerFacade LOGGER) {
		this.urlRepository = urlRepository;
		this.LOGGER = LOGGER;
	}

	public void save(Url url) {
		LOGGER.info("SaveUrlShortenedServiceImpl save Begin >>");
		LOGGER.info(String.format("url ==> [ %s ]", url));
		urlRepository.save(url);
		LOGGER.info("SaveUrlShortenedServiceImpl save End <<");
	}

}
