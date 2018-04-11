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
public class UserMention  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("screen_name")
    private String screenName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("id_str")
    private String idStr;

    @JsonProperty("indices")
    private List<Integer> indices;

}
