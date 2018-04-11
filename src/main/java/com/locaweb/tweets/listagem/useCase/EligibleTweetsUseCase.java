package com.locaweb.tweets.listagem.useCase;

import com.locaweb.tweets.listagem.domains.eligibleTweets.Mention;
import com.locaweb.tweets.listagem.domains.eligibleTweets.Mention.MentionBuilder;
import com.locaweb.tweets.listagem.domains.eligibleTweets.Relevant;
import com.locaweb.tweets.listagem.domains.eligibleTweets.Relevant.RelevantBuilder;
import com.locaweb.tweets.listagem.domains.twitter.Status;
import com.locaweb.tweets.listagem.gateways.TwitterGateway;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EligibleTweetsUseCase {

    private final TwitterGateway twitterGateway;

    public List<Relevant> mostRelevants() {
        return findAllTweepsEligibles()
                .stream()
                .map(this::buildRelevant)
                .sorted(Comparator.comparingInt(Relevant::getFollowersCount)
                        .thenComparingInt(Relevant::getRetweetCount)
                        .thenComparingInt(Relevant::getFavoriteCount)
                        .reversed()
                )
                .collect(toList());
    }

    public List<ModelMap> mostMentions(){
        return findAllTweepsEligibles()
                .stream()
                .map(this::buildMention)
                .collect(Collectors.groupingBy(Mention::getScreenName))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .sorted(Collections.reverseOrder(Comparator.comparing(List::size)))
                .map(mentions -> new ModelMap(mentions.iterator().next().getScreenName(), mentions))
                .collect(Collectors.toList());
    }

    private List<Status> findAllTweepsEligibles() {
        return twitterGateway.findAllTweeps()
                .getStatuses()
                .stream()
                .filter(status -> StringUtils.contains(status.getText(), "@locaweb"))
                .filter(status -> Objects.isNull(status.getInReplyToStatusId()))
                .collect(toList());
    }

    private Mention buildMention(final Status status) {
        final MentionBuilder builder = Mention.builder()
                .followersCount(status.getUser().getFollowersCount())
                .screenName(status.getUser().getScreenName())
                .createdAt(status.getCreatedAt())
                .retweetCount(status.getRetweetCount())
                .text(status.getText())
                .favoriteCount(status.getFavoriteCount());

        CollectionUtils.emptyIfNull(status.getUser().getEntities().getUrl().getUrls())
                .stream()
                .findFirst()
                .ifPresent(urlItem -> {
                    builder.link(urlItem.getExpandedUrl());
                    builder.profileLink(urlItem.getUrl());
                });

        return builder.build();
    }

    private Relevant buildRelevant(final Status status) {
        final RelevantBuilder builder = Relevant
                .builder()
                .followersCount(status.getUser().getFollowersCount())
                .screenName(status.getUser().getScreenName())
                .createdAt(status.getCreatedAt())
                .retweetCount(status.getRetweetCount())
                .text(status.getText())
                .favoriteCount(status.getFavoriteCount());

        CollectionUtils.emptyIfNull(status.getUser().getEntities().getUrl().getUrls())
                .stream()
                .findFirst()
                .ifPresent(urlItem -> {
                    builder.link(urlItem.getExpandedUrl());
                    builder.profileLink(urlItem.getUrl());
                });

        return builder.build();
    }
}
