package br.com.backendbrasil.urlshortener.validator;

import org.springframework.validation.Errors;

public abstract class AbstractValidator<T> implements CommonValidator<T> {

	private static final long serialVersionUID = 1508200015766707744L;
	
	private final Class<T> clazz;

	public AbstractValidator(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return this.clazz.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		@SuppressWarnings("unchecked")
		T object = (T) target;
		boolean isValid = isValid(object);
		if (!isValid) {
			errors.rejectValue(getFieldName(), null, String.format("Field ==> [ %s ] invalid", getFieldName()));
		}
	}

}
