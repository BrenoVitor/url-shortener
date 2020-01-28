package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;

public interface ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService extends Serializable {

	boolean isDuplicated(UrlShortenedDto urlShortenedDto);

}
