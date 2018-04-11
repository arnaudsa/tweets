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
public class UrlItem  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("expanded_url")
    private String expandedUrl;

    @JsonProperty("url")
    private String url;

    @JsonProperty("indices")
    private List<Integer> indices;

}
