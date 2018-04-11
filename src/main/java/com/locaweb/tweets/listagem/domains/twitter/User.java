package com.locaweb.tweets.listagem.domains.twitter;

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
public class User  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("profile_sidebar_fill_color")
    private String profileSidebarFillColor;

    @JsonProperty("profile_sidebar_border_color")
    private String profileSidebarBorderColor;

    @JsonProperty("profile_background_tile")
    private boolean profileBackgroundTile;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("location")
    private String location;

    @JsonProperty("follow_request_sent")
    private String followRequestSent;

    @JsonProperty("profile_link_color")
    private String profileLinkColor;

    @JsonProperty("is_translator")
    private boolean isTranslator;

    @JsonProperty("id_str")
    private String idStr;

    @JsonProperty("entities")
    private UserEntitie entities;

    @JsonProperty("default_profile")
    private boolean defaultProfile;

    @JsonProperty("contributors_enabled")
    private boolean contributorsEnabled;

    @JsonProperty("favourites_count")
    private Integer favouritesCount;

    @JsonProperty("url")
    private String url;

    @JsonProperty("profile_image_url_https")
    private String profileImageUrlHttps;

    @JsonProperty("utc_offset")
    private Integer utcOffset;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("profile_use_background_image")
    private boolean profileUseBackgroundImage;

    @JsonProperty("listed_count")
    private Integer listedCount;

    @JsonProperty("profile_text_color")
    private String profileTextColor;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("followers_count")
    private Integer followersCount;

    @JsonProperty("protected")
    private boolean preserved;

    @JsonProperty("notifications")
    private String notifications;

    @JsonProperty("profile_background_image_url_https")
    private String profileBackgroundImageUrlHttps;

    @JsonProperty("profile_background_color")
    private String profileBackgroundColor;

    @JsonProperty("verified")
    private boolean verified;

    @JsonProperty("geo_enabled")
    private boolean geoEnabled;

    @JsonProperty("time_zone")
    private String timeZone;

    @JsonProperty("description")
    private String description;

    @JsonProperty("default_profile_image")
    private boolean defaultProfileImage;

    @JsonProperty("profile_background_image_url")
    private String profileBackgroundImageUrl;

    @JsonProperty("statuses_count")
    private Integer statusesCount;

    @JsonProperty("friends_count")
    private Integer friendsCount;

    @JsonProperty("following")
    private String following;

    @JsonProperty("show_all_inline_media")
    private boolean showAllInlineMedia;

    @JsonProperty("screen_name")
    private String screenName;

}
