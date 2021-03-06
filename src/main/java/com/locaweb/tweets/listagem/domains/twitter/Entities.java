package com.locaweb.tweets.listagem.domains.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entities implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("urls")
    private List<String> urls;

    @JsonProperty("hashtags")
    private List<String> hashTags;

    @JsonProperty("user_mentions")
    private List<UserMention> userMentions;

}
