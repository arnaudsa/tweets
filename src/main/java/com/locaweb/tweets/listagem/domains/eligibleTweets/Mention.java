package com.locaweb.tweets.listagem.domains.eligibleTweets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mention implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("screen_name")
    private String screenName;

    @JsonProperty("profile_link")
    private String profileLink;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("favorite_count")
    private Integer favoriteCount;

    @JsonProperty("followers_count")
    private Integer followersCount;

    @JsonProperty("text")
    private String text;

    @JsonProperty("link")
    private String link;

    @JsonProperty("retweet_count")
    private Integer retweetCount;

}
