package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.NotFoundUrlByShortAddressAdValidToAfter;
import br.com.backendbrasil.urlshortener.model.Url;

public interface GetUrlOriginalByShortenedUrlService extends Serializable {

	Url get(UrlShortenedDto urlShortenedDto) throws NotFoundUrlByShortAddressAdValidToAfter;

}
