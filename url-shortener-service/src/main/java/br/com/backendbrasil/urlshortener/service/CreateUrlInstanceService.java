package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.model.Url;

public interface CreateUrlInstanceService extends Serializable {
	
	Url create(UrlOriginalDto urlOriginalDto, UrlShortenedDto urlShortenedDto);

}
