package br.com.backendbrasil.urlshortener.helper;

import java.io.Serializable;

public interface UrlValidatorHelper extends Serializable {
	
	boolean validate(String url);

}
