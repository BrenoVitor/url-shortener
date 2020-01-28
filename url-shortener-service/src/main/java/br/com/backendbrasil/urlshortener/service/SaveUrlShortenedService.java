package br.com.backendbrasil.urlshortener.service;

import java.io.Serializable;

import br.com.backendbrasil.urlshortener.model.Url;

public interface SaveUrlShortenedService extends Serializable {
	
	void save(Url url);

}
