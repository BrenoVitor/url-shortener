package br.com.backendbrasil.urlshortener.exception;

public class GenerateUrlShortenedDuplicatedException extends Exception {

	private static final long serialVersionUID = 5690875210692684744L;

	public GenerateUrlShortenedDuplicatedException(final String message) {
		super(message);
	}

	public GenerateUrlShortenedDuplicatedException(final String message, final Throwable t) {
		super(message, t);
	}

}
