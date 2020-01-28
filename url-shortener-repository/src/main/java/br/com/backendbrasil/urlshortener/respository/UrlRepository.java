package br.com.backendbrasil.urlshortener.respository;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backendbrasil.urlshortener.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>, Serializable {

	Optional<Url> findByShortAddressAndValidToAfter(String shortAddress, Date validTo);
	
	Optional<Url> findByOriginAddress(String originAddress);


}
