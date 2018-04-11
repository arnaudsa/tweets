package com.locaweb.tweets.listagem.gateways.impl;

import com.locaweb.tweets.listagem.domains.exception.ServiceException;
import com.locaweb.tweets.listagem.domains.twitter.TwitterResponse;
import com.locaweb.tweets.listagem.gateways.TwitterGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterGatewayImpl implements TwitterGateway {

    private static final String MSG_ERROR = "Error in findAllTweeps";

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    @Value("${endpoints.twitter.host}${endpoints.twitter.urls.tweeps}")
    private String url;

    @Override
    public TwitterResponse findAllTweeps() {
        try {
            final HttpEntity<TwitterResponse> httpEntity = new HttpEntity<>(httpHeaders);
            final ResponseEntity<TwitterResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TwitterResponse.class);
            return responseEntity.getBody();

        }catch (final HttpClientErrorException exception){
            log.error(MSG_ERROR, exception);
            throw new ServiceException(MSG_ERROR, exception);
        }
    }
}
