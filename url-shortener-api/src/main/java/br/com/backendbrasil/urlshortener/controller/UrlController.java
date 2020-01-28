package br.com.backendbrasil.urlshortener.controller;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;

public interface UrlController extends Serializable {
	
	ResponseEntity<?> shorten(UrlOriginalDto urlOriginalDto, UriComponentsBuilder uriBuilder);
	
	ResponseEntity<?> getOriginalUrl( String urlShortened);

}
