package br.com.backendbrasil.urlshortener.controller.advice;

import java.io.Serializable;
import java.util.Collection;

public interface ControllerAdvice<T, K extends Exception> extends Serializable {
	
	Collection<T> handle(K exception);

}
