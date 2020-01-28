package br.com.backendbrasil.urlshortener.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class Url implements Serializable {

	private static final long serialVersionUID = -3494149535544730586L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Positive
	private Long id;

	@URL
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 10)
	private String originAddress;

	@Column(unique = true, nullable = false, length = 10)
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5, max = 10)
	private String shortAddress;

	@FutureOrPresent
	@NotNull
	private Date validTo;

	@SuppressWarnings("unused")
	@Deprecated
	private Url() {
	}

	public Url(@URL @NotNull @NotEmpty @NotBlank @Size(min = 10) final String originAddress,
			@NotNull @NotEmpty @NotBlank @Size(min = 5, max = 10) final String shortAddress,
			@FutureOrPresent @NotNull final Date validTo) {
		super();
		this.originAddress = originAddress;
		this.shortAddress = shortAddress;
		this.validTo = validTo;
	}

	public Long getId() {
		return id;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public String getShortAddress() {
		return shortAddress;
	}

	public Date getValidTo() {
		return validTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((originAddress == null) ? 0 : originAddress.hashCode());
		result = prime * result + ((shortAddress == null) ? 0 : shortAddress.hashCode());
		result = prime * result + ((validTo == null) ? 0 : validTo.hashCode());
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
		Url other = (Url) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (originAddress == null) {
			if (other.originAddress != null)
				return false;
		} else if (!originAddress.equals(other.originAddress))
			return false;
		if (shortAddress == null) {
			if (other.shortAddress != null)
				return false;
		} else if (!shortAddress.equals(other.shortAddress))
			return false;
		if (validTo == null) {
			if (other.validTo != null)
				return false;
		} else if (!validTo.equals(other.validTo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", originAddress=" + originAddress + ", shortAddress=" + shortAddress + ", validTo="
				+ validTo + "]";
	}

}
