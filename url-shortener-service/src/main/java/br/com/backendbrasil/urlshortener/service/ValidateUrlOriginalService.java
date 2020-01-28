package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;

public interface ValidateUrlOriginalService extends Serializable {
	
	boolean isValid(UrlOriginalDto urlOriginalDto);

}
