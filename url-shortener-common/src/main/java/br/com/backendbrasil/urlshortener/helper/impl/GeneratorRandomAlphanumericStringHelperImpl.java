package br.com.backendbrasil.urlshortener.helper.impl;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.backendbrasil.urlshortener.helper.GeneratorRandomAlphanumericStringHelper;

public class GeneratorRandomAlphanumericStringHelperImpl implements GeneratorRandomAlphanumericStringHelper {

	private static final long serialVersionUID = -5557868513420452760L;

	public String generate(int minLengthInclusive, int maxLengthExclusive) {
		return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);
	}

}
