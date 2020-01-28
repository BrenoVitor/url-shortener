package br.com.backendbrasil.urlshortener.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UrlOriginalDto implements Serializable {

	private static final long serialVersionUID = -5880621138742639519L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 10)
	private String address;

	@SuppressWarnings("unused")
	@Deprecated
	private UrlOriginalDto() {
	}

	public UrlOriginalDto(@NotNull @NotEmpty @NotBlank @Size(min = 10) final String address) {
		super();
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		UrlOriginalDto other = (UrlOriginalDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UrlOriginalDto [address=" + address + "]";
	}

}
