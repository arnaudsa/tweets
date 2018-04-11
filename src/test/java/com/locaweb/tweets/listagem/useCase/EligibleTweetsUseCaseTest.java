package com.locaweb.tweets.listagem.useCase;

import com.locaweb.tweets.listagem.domains.eligibleTweets.Mention;
import com.locaweb.tweets.listagem.domains.eligibleTweets.Relevant;
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
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EligibleTweetsUseCaseTest {

    @Autowired
    private EligibleTweetsUseCase eligibleTweetsUseCase;

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
    public void mostRelevants() throws IOException {

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

        final List<Relevant> relevants = eligibleTweetsUseCase.mostRelevants();

        // THEN
        assertThat(relevants)
                .isNotEmpty();

        final Relevant firstRelevant = relevants.iterator().next();

        assertThat(firstRelevant)
                .isNotNull();

        assertThat(firstRelevant.getText())
                .contains("@locaweb");

        assertThat(relevants.size())
                .isGreaterThan(1);

        final Relevant secondRelevant = relevants.get(1);

        assertThat(firstRelevant.getFollowersCount())
                .isGreaterThan(secondRelevant.getFollowersCount());

    }

    @Test
    public void mostMentions() throws IOException {

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

        final List<ModelMap> mentions = eligibleTweetsUseCase.mostMentions();

        // THEN
        assertThat(mentions)
                .isNotEmpty();

        assertThat(mentions.size())
                .isGreaterThan(2);

        final ModelMap firstModelMap = mentions.get(INTEGER_ZERO);
        assertThat(firstModelMap)
                .isNotNull();

        final List<Mention> firstMentions = (List<Mention>) firstModelMap.get(firstModelMap.keySet().iterator().next());
        assertThat(firstMentions)
                .isNotNull();

        final ModelMap secondModelMap = mentions.get(INTEGER_ONE);
        assertThat(secondModelMap)
                .isNotNull();

        final List<Mention> secondMentions = (List<Mention>) firstModelMap.get(firstModelMap.keySet().iterator().next());
        assertThat(secondMentions)
                .isNotNull();

        assertThat(firstMentions.size())
                .isGreaterThanOrEqualTo(secondMentions.size());

    }
}