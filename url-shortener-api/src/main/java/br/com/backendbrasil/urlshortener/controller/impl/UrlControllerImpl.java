package br.com.backendbrasil.urlshortener.controller.impl;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sun.istack.NotNull;

import br.com.backendbrasil.urlshortener.controller.UrlController;
import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;
import br.com.backendbrasil.urlshortener.exception.NotFoundUrlByShortAddressAdValidToAfter;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.service.CreateUrlInstanceService;
import br.com.backendbrasil.urlshortener.service.GetUrlOriginalByShortenedUrlService;
import br.com.backendbrasil.urlshortener.service.SaveUrlShortenedService;
import br.com.backendbrasil.urlshortener.service.UrlShortenerService;
import br.com.backendbrasil.urlshortener.validator.CommonValidator;

@RestController
@RequestMapping("url")
public class UrlControllerImpl implements UrlController {

	private static final long serialVersionUID = -4979794607062875828L;

	private final UrlShortenerService urlShortenerService;

	private final GetUrlOriginalByShortenedUrlService getUrlOriginalByShortenedUrlService;

	private final SaveUrlShortenedService saveUrlShortenedService;

	private final CreateUrlInstanceService createUrlInstanceService;

	private final CommonValidator<UrlOriginalDto> commonValidator;

	private final LoggerFacade LOGGER;

	public UrlControllerImpl(final UrlShortenerService urlShortenerService,
			final GetUrlOriginalByShortenedUrlService getUrlOriginalByShortenedUrlService,
			final SaveUrlShortenedService saveUrlShortenedServiceImpl,
			final CreateUrlInstanceService createUrlInstanceService,
			final CommonValidator<UrlOriginalDto> commonValidator, final LoggerFacade LOGGER) {
		this.urlShortenerService = urlShortenerService;
		this.getUrlOriginalByShortenedUrlService = getUrlOriginalByShortenedUrlService;
		this.saveUrlShortenedService = saveUrlShortenedServiceImpl;
		this.createUrlInstanceService = createUrlInstanceService;
		this.commonValidator = commonValidator;
		this.LOGGER = LOGGER;
	}

	@InitBinder("urlOriginalDto")
	public void initBind(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(this.commonValidator);
	}

	@PostMapping
	@CacheEvict(value = "originalUrl", allEntries = true)
	public ResponseEntity<?> shorten(@RequestBody @Valid @NotNull final UrlOriginalDto urlOriginalDto,
			final UriComponentsBuilder uriBuilder) {
		LOGGER.info("UrlControllerImpl shorten Begin >>");
		LOGGER.info(urlOriginalDto.toString());
		UrlShortenedDto urlShortenedDto;
		try {
			urlShortenedDto = urlShortenerService.shorten(urlOriginalDto);
		} catch (GenerateUrlShortenedDuplicatedException e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseEntity.status(500).body(e.getMessage());
		}
		final Url url = createUrlInstanceService.create(urlOriginalDto, urlShortenedDto);
		saveUrlShortenedService.save(url);
		LOGGER.info(urlShortenedDto.toString());
		LOGGER.info(url.toString());
		final URI uri = uriBuilder.path("/url/{urlShortened}").buildAndExpand(urlShortenedDto.getShortenedAddress())
				.toUri();
		LOGGER.info("UrlControllerImpl shorten End <<");
		return ResponseEntity.created(uri).body(urlShortenedDto);
	}

	@GetMapping("{urlShortened}")
	@Cacheable(value = "originalUrl")
	public ResponseEntity<?> getOriginalUrl(
			@PathVariable(name = "urlShortened", required = true) @Valid @NotNull @NotEmpty @NotBlank String urlShortened) {
		LOGGER.info("UrlControllerImpl getOriginalUrl Begin >>");
		LOGGER.info(String.format("urlShortened ==> [ %s ]", urlShortened));
		final UrlShortenedDto urlShortenedDto = new UrlShortenedDto(urlShortened);
		LOGGER.info(urlShortenedDto.toString());
		Url url;
		try {
			url = getUrlOriginalByShortenedUrlService.get(urlShortenedDto);
		} catch (NotFoundUrlByShortAddressAdValidToAfter e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		LOGGER.info(url.toString());
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto(url.getOriginAddress());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(urlOriginalDto.getAddress()));
		LOGGER.info("UrlControllerImpl getOriginalUrl End <<");
		return new ResponseEntity<UrlOriginalDto>(headers, HttpStatus.PERMANENT_REDIRECT);
	}

}
