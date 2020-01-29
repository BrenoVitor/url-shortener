package br.com.backendbrasil.urlshortener.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig implements Serializable {
	
	private static final long serialVersionUID = -1336425125147202444L;

	@Value("${allowed.credentials}")
	private String isAllowedCredentials;

	@Value("${allowed.origin}")
	private String allowedOrigin;
	
	@Value("${allowed.header}")
	private String allowedHeader;
	
	@Value("${allowed.methods}")
	private String allowedMethods;
	
	@Value("${allowed.methods.separator}")
	private String allowedMethodsSeparator;
	

	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(Boolean.parseBoolean(isAllowedCredentials));
        config.addAllowedOrigin(allowedOrigin);
        config.addAllowedHeader(allowedHeader);
        String[] allowedMethod = allowedMethods.split(allowedMethodsSeparator);
        for (String method : allowedMethod) {
        	config.addAllowedMethod(method);
		}
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
