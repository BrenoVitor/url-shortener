package br.com.backendbrasil.urlshortener.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UrlShortenedDto implements Serializable {

	private static final long serialVersionUID = 2209469697108334633L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5, max = 10)
	private String shortenedAddress;

	@SuppressWarnings("unused")
	@Deprecated
	private UrlShortenedDto() {
	}

	public UrlShortenedDto(@NotNull @NotEmpty @NotBlank @Size(min = 5, max = 10) final String shortenedAddress) {
		super();
		this.shortenedAddress = shortenedAddress;
	}

	public String getShortenedAddress() {
		return shortenedAddress;
	}

	@Override
	public String toString() {
		return "UrlShortenedDto [shortenedAddress=" + shortenedAddress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shortenedAddress == null) ? 0 : shortenedAddress.hashCode());
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
		UrlShortenedDto other = (UrlShortenedDto) obj;
		if (shortenedAddress == null) {
			if (other.shortenedAddress != null)
				return false;
		} else if (!shortenedAddress.equals(other.shortenedAddress))
			return false;
		return true;
	}

}
