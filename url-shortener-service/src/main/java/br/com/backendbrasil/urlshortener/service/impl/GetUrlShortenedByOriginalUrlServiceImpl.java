package br.com.backendbrasil.urlshortener.service.impl;

import java.util.Optional;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.GetUrlShortenedByOriginalUrlService;


public class GetUrlShortenedByOriginalUrlServiceImpl implements GetUrlShortenedByOriginalUrlService {

	private static final long serialVersionUID = 4390084613850005179L;
	
	private final UrlRepository urlRepository;
	
	public GetUrlShortenedByOriginalUrlServiceImpl(final UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	public Url get(UrlOriginalDto urlOriginalDto) {
		Optional<Url> findByOriginalAddress = urlRepository.findByOriginAddress(urlOriginalDto.getAddress());
		return findByOriginalAddress.get();
	}

}
