package br.com.backendbrasil.urlshortener.respository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.backendbrasil.urlshortener.model.Url;

@DataJpaTest
public class UrlRepositoryTest {

	@Autowired
	private UrlRepository urlRepository;

	@Test
	public void saveHttpTest() {
		Url save = urlRepository.save(new Url("http://www.google.com.br", "abdcef", new Date()));
		Optional<Url> findById = urlRepository.findById(save.getId());
		assertTrue(findById.isPresent());
		assertEquals(save, findById.get());
	}
	
	@Test
	public void saveHttpsTest() {
		Url save = urlRepository.save(new Url("https://www.google.com.br", "abdcef", new Date()));
		Optional<Url> findById = urlRepository.findById(save.getId());
		assertTrue(findById.isPresent());
		assertEquals(save, findById.get());
	}

	@Test
	public void saveInvalidUrlTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("google.com.br", "abdcef", new Date()));
		});
	}

	@Test
	public void saveNullShortenedUrlTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("http://www.google.com.br", null, new Date()));
		});
	}

	@Test
	public void saveEmptyShortenedUrlTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("http://www.google.com.br", "", new Date()));
		});
	}

	@Test
	public void saveNullUrlTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url(null, "abdcef", new Date()));
		});
	}

	@Test
	public void saveEmptyUrlTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("", "abdcef", new Date()));
		});
	}

	@Test
	public void saveNullDateTest() {
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("http://www.google.com.br", "abdcef", null));
		});
	}

	@Test
	public void savePastDateTest() {
		Date date = new Date();
		assertThrows(ConstraintViolationException.class, () -> {
			urlRepository.saveAndFlush(new Url("http://www.google.com.br", "abdcef", date));
		});
	}

	@Test
	public void findByIdTest() {
		Url url = new Url("http://www.google.com.br", "abdcef", new Date());
		urlRepository.save(url);
		Optional<Url> optionalUrl = urlRepository.findById(url.getId());
		assertEquals(url, optionalUrl.get());
	}

	@Test
	public void findByShortAddressAndValidToAfterTest() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Url url = new Url("http://www.google.com.br", "abdcef", calendar.getTime());
		urlRepository.save(url);
		Optional<Url> optionalUrl = urlRepository.findByShortAddressAndValidToAfter(url.getShortAddress(), new Date());
		assertEquals(url, optionalUrl.get());
	}

	@Test
	public void findByOriginAddressTest() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Url url = new Url("http://www.google.com.br", "abdcef", calendar.getTime());
		urlRepository.save(url);
		Optional<Url> optionalUrl = urlRepository.findByOriginAddress("http://www.google.com.br");
		assertEquals(url, optionalUrl.get());
	}
}
