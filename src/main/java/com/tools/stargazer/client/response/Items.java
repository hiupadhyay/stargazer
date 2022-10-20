package com.tools.stargazer.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Items {


    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("html_url")
    private String url;
    @JsonProperty("stargazers_count")
    private int stars;
    @JsonProperty("language")
    private String language;

}
