package br.com.backendbrasil.urlshortener.helper;

import java.io.Serializable;

public interface GeneratorRandomAlphanumericStringHelper extends Serializable {
	
	String generate(int minLengthInclusive, int maxLengthExclusive);

}
