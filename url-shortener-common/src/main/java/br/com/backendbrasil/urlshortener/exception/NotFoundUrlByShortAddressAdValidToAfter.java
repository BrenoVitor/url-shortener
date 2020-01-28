package br.com.backendbrasil.urlshortener.exception;

public class NotFoundUrlByShortAddressAdValidToAfter extends Exception {

	private static final long serialVersionUID = -708671316611621353L;

	public NotFoundUrlByShortAddressAdValidToAfter(final String message) {
		super(message);
	}

	public NotFoundUrlByShortAddressAdValidToAfter(final String message, final Throwable t) {
		super(message, t);
	}

}
