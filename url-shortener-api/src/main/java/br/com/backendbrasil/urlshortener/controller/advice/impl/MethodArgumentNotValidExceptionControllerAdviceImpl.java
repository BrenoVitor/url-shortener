package br.com.backendbrasil.urlshortener.controller.advice.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.backendbrasil.urlshortener.controller.advice.ControllerAdvice;
import br.com.backendbrasil.urlshortener.dto.MethodArgumentNotValidExceptionDto;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionControllerAdviceImpl
		implements ControllerAdvice<MethodArgumentNotValidExceptionDto, MethodArgumentNotValidException> {

	private static final long serialVersionUID = -2155104363204676526L;
	
	private final MessageSource messageSource;

	@Autowired
	public MethodArgumentNotValidExceptionControllerAdviceImpl(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@Override
	public final Collection<MethodArgumentNotValidExceptionDto> handle(
			final MethodArgumentNotValidException exception) {
		final Collection<MethodArgumentNotValidExceptionDto> methodArgumentNotValidExceptionDtoCollection = new ArrayList<MethodArgumentNotValidExceptionDto>();
		final Collection<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			final String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			final MethodArgumentNotValidExceptionDto methodArgumentNotValidExceptionDto = new MethodArgumentNotValidExceptionDto(
					e.getField(), message);
			methodArgumentNotValidExceptionDtoCollection.add(methodArgumentNotValidExceptionDto);
		});
		return methodArgumentNotValidExceptionDtoCollection;
	}

}
