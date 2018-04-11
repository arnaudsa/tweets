package com.locaweb.tweets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Value("${endpoints.twitter.timeOut}")
    private int timeout;

    @Value("${endpoints.twitter.username}")
    private String username;

    @Bean
    public RestTemplate restTemplate(final MappingJackson2HttpMessageConverter mappingJacksonConverter) {

        final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setBufferRequestBody(false);
        factory.setConnectTimeout(timeout);

        final RestTemplate rest = new RestTemplate(factory);
        rest.getMessageConverters().add(0, mappingJacksonConverter);

        return rest;

    }

    @Bean
    public HttpHeaders httpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("Username", username);
        return httpHeaders;
    }


}
