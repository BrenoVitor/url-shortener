package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.model.Url;

public interface GetUrlShortenedByOriginalUrlService extends Serializable {

	Url get(UrlOriginalDto urlOriginalDto);

}
