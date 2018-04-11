package com.locaweb.tweets.listagem.gateways;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.locaweb.tweets.listagem.domains.twitter.TwitterResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadTwitterResponseTest {

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Test
    public void readJsonTwitterResponse() throws IOException {

        final InputStream file = getClass().getResourceAsStream("/twitterResponse.json");
        final TypeReference<TwitterResponse> typeReferene = new TypeReference<TwitterResponse>() {};

        final TwitterResponse twitterResponse = jsonObjectMapper.readValue(file, typeReferene);

        assertThat(twitterResponse)
                .isNotNull();

        assertThat(twitterResponse.getStatuses())
                .hasSize(51);

    }
}
