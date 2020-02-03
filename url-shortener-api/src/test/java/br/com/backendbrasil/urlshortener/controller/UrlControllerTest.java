package br.com.backendbrasil.urlshortener.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private UrlRepository urlRepository;

	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder();
		}
	}

	@Test
	public void testShortenUrlReturn201() {
		BDDMockito.when(urlRepository.save(any(Url.class)))
				.thenReturn(new Url("http://www.google.com.br", "abcdef", new Date()));
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
		ResponseEntity<UrlShortenedDto> response = restTemplate.postForEntity("/url", urlOriginalDto,
				UrlShortenedDto.class);
		BDDMockito.verify(urlRepository, times(1)).save(any(Url.class));
		BDDMockito.verify(urlRepository, times(1)).findByShortAddressAndValidToAfter(any(String.class),
				any(Date.class));
		assertEquals(201, response.getStatusCodeValue());
	}
	
	@Test
	public void testShortenUrlDuplicatedShortenedUrlReturn400() {
		BDDMockito.when(urlRepository.save(any(Url.class)))
				.thenReturn(new Url("http://www.google.com.br", "abcdef", new Date()));
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.of(new Url("http://www.google.com.br", "abcdef", new Date())));
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
		assertThrows(RestClientException.class, () -> {
			ResponseEntity<UrlShortenedDto> responseEntity = restTemplate.postForEntity("/url", urlOriginalDto, UrlShortenedDto.class);
			assertEquals(500, responseEntity.getStatusCodeValue());
		});
	}

	@Test
	public void testShortenUrlInvalidUrlReturn400() {
		BDDMockito.when(urlRepository.save(any(Url.class)))
				.thenReturn(new Url("http://www.google.com.br", "abcdef", new Date()));
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("www.google.com.br");
		ResponseEntity<String> response = restTemplate.postForEntity("/url", urlOriginalDto, String.class);
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void testShortenUrlNullUrlReturn400() {
		BDDMockito.when(urlRepository.save(any(Url.class)))
				.thenReturn(new Url("http://www.google.com.br", "abcdef", new Date()));
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto(null);
		ResponseEntity<String> response = restTemplate.postForEntity("/url", urlOriginalDto, String.class);
		assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	public void testShortenUrlEmptyUrlReturn400() {
		BDDMockito.when(urlRepository.save(any(Url.class)))
				.thenReturn(new Url("http://www.google.com.br", "abcdef", new Date()));
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("");
		ResponseEntity<String> response = restTemplate.postForEntity("/url", urlOriginalDto, String.class);
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void testGetOriginalUrlReturn404() {
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		ResponseEntity<UrlOriginalDto> response = restTemplate.getForEntity("/url/bmng", UrlOriginalDto.class);
		assertEquals(404, response.getStatusCodeValue());
	}

	@Test
	public void testGetOriginalUrlReturn308() {
		BDDMockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.of(new Url("http://www.google.com.br", "abcdef", new Date())));
		ResponseEntity<UrlOriginalDto> response = restTemplate.getForEntity("/url/abcdef", UrlOriginalDto.class);
		assertEquals(308, response.getStatusCodeValue());
	}

}
