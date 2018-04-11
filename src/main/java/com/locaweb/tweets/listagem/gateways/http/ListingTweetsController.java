package com.locaweb.tweets.listagem.gateways.http;

import com.locaweb.tweets.listagem.domains.eligibleTweets.Relevant;
import com.locaweb.tweets.listagem.useCase.EligibleTweetsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ListingTweetsController {

    private final EligibleTweetsUseCase eligibleTweetsUseCase;

    @GetMapping(value = "/most_relevants")
    public ResponseEntity<List<Relevant>> mostRelevants() {
        return new ResponseEntity<>(eligibleTweetsUseCase.mostRelevants(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/most_mentions")
    public ResponseEntity<List<ModelMap>> mostMentions() {
        return new ResponseEntity<>(eligibleTweetsUseCase.mostMentions(), HttpStatus.ACCEPTED);
    }

}
