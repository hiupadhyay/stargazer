package com.tools.stargazer.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Repository {

    @JsonProperty("items")
    private List<Items> items;
}
