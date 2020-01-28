package br.com.backendbrasil.urlshortener.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class MethodArgumentNotValidExceptionDto {

	@NotEmpty
	@NotBlank
	@NotNull
	private String field;

	@NotEmpty
	@NotBlank
	@NotNull
	private String error;

	public MethodArgumentNotValidExceptionDto(@NotEmpty @NotBlank final String field,
			@NotEmpty @NotBlank final String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodArgumentNotValidExceptionDto other = (MethodArgumentNotValidExceptionDto) obj;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MethodArgumentNotValidExceptionDto [field=" + field + ", error=" + error + "]";
	}

}
