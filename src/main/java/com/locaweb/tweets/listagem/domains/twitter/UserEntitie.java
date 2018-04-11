package com.locaweb.tweets.listagem.domains.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntitie  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("url")
    private Url url;

    @JsonProperty("description")
    private Description description;

}
