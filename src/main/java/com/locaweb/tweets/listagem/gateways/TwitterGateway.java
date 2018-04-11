package com.locaweb.tweets.listagem.gateways;

import com.locaweb.tweets.listagem.domains.twitter.TwitterResponse;

public interface TwitterGateway {

    TwitterResponse findAllTweeps();

}
