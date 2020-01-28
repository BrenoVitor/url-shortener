package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;

public interface UrlShortenerService extends Serializable {

	UrlShortenedDto shorten(UrlOriginalDto urlOriginalDto) throws GenerateUrlShortenedDuplicatedException;

}
