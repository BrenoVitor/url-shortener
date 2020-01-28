package br.com.backendbrasil.urlshortener.validator;

import java.io.Serializable;

import org.springframework.validation.Validator;

public interface CommonValidator<T> extends Validator, Serializable {

	boolean isValid(T object);

	String getFieldName();

}
