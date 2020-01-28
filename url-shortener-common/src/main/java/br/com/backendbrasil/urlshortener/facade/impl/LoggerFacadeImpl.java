package br.com.backendbrasil.urlshortener.facade.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.backendbrasil.urlshortener.facade.LoggerFacade;

public class LoggerFacadeImpl implements LoggerFacade {

	private static final long serialVersionUID = 8589627212389953046L;
	
	private static final Logger LOGGER = LogManager.getLogger("urlShortenerLogger");

	@Override
	public void trace(String message) {
		LOGGER.trace(message);
	}

	@Override
	public void trace(String message, Throwable throwable) {
		LOGGER.trace(message, throwable);
	}

	@Override
	public void debug(String message) {
		LOGGER.debug(message);
	}

	@Override
	public void debug(String message, Throwable throwable) {
		LOGGER.debug(message, throwable);
	}

	@Override
	public void info(String message) {
		LOGGER.info(message);
	}

	@Override
	public void info(String message, Throwable throwable) {
		LOGGER.info(message, throwable);
	}

	@Override
	public void warn(String message) {
		LOGGER.warn(message);
	}

	@Override
	public void warn(String message, Throwable throwable) {
		LOGGER.warn(message, throwable);
	}

	@Override
	public void error(String message) {
		LOGGER.warn(message);
	}

	@Override
	public void error(String message, Throwable throwable) {
		LOGGER.error(message, throwable);

	}

	@Override
	public void fatal(String message) {
		LOGGER.fatal(message);
	}

	@Override
	public void fatal(String message, Throwable throwable) {
		LOGGER.fatal(message, throwable);

	}

}
