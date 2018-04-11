package com.locaweb.tweets.listagem.gateways;

import com.locaweb.tweets.listagem.domains.exception.ServiceException;
import com.locaweb.tweets.listagem.domains.twitter.TwitterResponse;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TwitterGatewayImplTest {

    @Autowired
    private TwitterGateway twitterGateway;

    @Value("${endpoints.twitter.urls.tweeps}")
    private String url;

    private ClientAndServer mockServer;

    @Before
    public void setUp(){
        mockServer = startClientAndServer(1080);
    }

    @After
    public void stopProxy() {
        mockServer.stop();
    }

    @Test
    public void findAllTweeps() throws IOException {

        // GIVEN
        final InputStream inputStream = getClass().getResourceAsStream("/twitterResponse.json");
        final String json = IOUtils.toString(inputStream);

        // WHEN
        mockServer
                .when(request()
                        .withPath(url))
                .respond(response()
                        .withHeaders(new Header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE))
                        .withBody(json)
                        .withStatusCode(HttpStatus.OK.value())
                );

        final TwitterResponse response = twitterGateway.findAllTweeps();

        // THEN
        assertThat(response)
                .isNotNull();

    }

    @Test
    public void findAllTweepsShouldReturnException() {

        try {
            // WHEN
            mockServer
                    .when(request()
                            .withPath(url))
                    .respond(response()
                            .withHeaders(new Header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE))
                            .withStatusCode(HttpStatus.NOT_FOUND.value())
                    );

            twitterGateway.findAllTweeps();

            Assert.fail("Error in findAllTweeps");

        } catch (final ServiceException exception) {
            // THEN
            assertThat("Error in findAllTweeps")
                    .isEqualTo(exception.getMessage());
        }

    }
}