package br.com.backendbrasil.urlshortener.service.impl;

import java.util.Date;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService;

public class ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl
		implements ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService {

	private static final long serialVersionUID = -9050384411119435082L;

	private final UrlRepository urlRepository;

	public ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(final UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	@Override
	public boolean isDuplicated(UrlShortenedDto urlShortenedDto) {
		return urlRepository.findByShortAddressAndValidToAfter(urlShortenedDto.getShortenedAddress(), new Date())
				.isPresent();
	}

}
