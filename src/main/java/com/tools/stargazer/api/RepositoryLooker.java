package com.tools.stargazer.api;

import java.util.Date;
import java.util.List;

import com.tools.stargazer.client.response.Items;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@RequestMapping("/api/repository/search")
public interface RepositoryLooker<T> {

    @GetMapping
    ResponseEntity<Mono<List<Items>>> find(
            @RequestParam("date")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date dateTime,
            @RequestParam(value = "order", required = false, defaultValue = "asc")
            final String order,
            @RequestParam(value = "sort", required = false, defaultValue = "stars")
            final String sortBy,
            @RequestParam(value = "language", required = false)
            final String programmingLanguage,
            @RequestParam(value = "limit", required = false, defaultValue = "100")
            final int limit);

}
