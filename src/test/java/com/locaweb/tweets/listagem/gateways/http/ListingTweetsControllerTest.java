package com.locaweb.tweets.listagem.gateways.http;

import org.apache.commons.io.IOUtils;
import org.junit.After;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ListingTweetsControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Value("${endpoints.twitter.urls.tweeps}")
    private String url;

    private MockMvc mockMvc;
    private ClientAndServer mockServer;

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(webAppContext).build();
        mockServer = startClientAndServer(1080);
    }

    @After
    public void stopProxy() {
        mockServer.stop();
    }

    @Test
    public void mostRelevants() throws Exception {

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

        final MvcResult mvcResult = mockMvc
                .perform(get("/most_relevants"))
                .andExpect(status().isAccepted())
                .andReturn();

        // THEN
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString)
                .isNotBlank();

    }

    @Test
    public void mostMentions() throws Exception {

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

        final MvcResult mvcResult = mockMvc
                .perform(get("/most_mentions"))
                .andExpect(status().isAccepted())
                .andReturn();

        // THEN
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString)
                .isNotBlank();

    }
}