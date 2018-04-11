package com.locaweb.tweets.listagem.domains.twitter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Status  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("coordinates")
    private String coordinates;

    @JsonProperty("favorited")
    private boolean favorited;

    @JsonProperty("truncated")
    private boolean truncated;

    //@JsonFormat(shape = STRING, pattern = "dd MMM HH:mm:ss Z yyyy")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("id_str")
    private String idStr;

    @JsonProperty("entities")
    private Entities entities;

    @JsonProperty("in_reply_to_user_id_str")
    private String inReplyToUserIdStr;

    @JsonProperty("contributors")
    private String contributors;

    @JsonProperty("text")
    private String text;

    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("retweet_count")
    private Integer retweetCount;

    @JsonProperty("in_reply_to_status_id_str")
    private String inReplyToStatusIdStr;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("geo")
    private String geo;

    @JsonProperty("retweeted")
    private boolean retweeted;

    @JsonProperty("in_reply_to_user_id")
    private Long inReplyToUserId;

    @JsonProperty("place")
    private String place;

    @JsonProperty("favorite_count")
    private Integer favoriteCount;

    @JsonProperty("user")
    private User user;

    @JsonProperty("in_reply_to_screen_name")
    private String inReplyToScreenName;

    @JsonProperty("source")
    private String source;

    @JsonProperty("in_reply_to_status_id")
    private Long inReplyToStatusId;

}
