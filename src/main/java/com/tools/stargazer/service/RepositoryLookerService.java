package com.tools.stargazer.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.tools.stargazer.client.RepositoryDataProvider;
import com.tools.stargazer.client.response.Items;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

@Service
public class RepositoryLookerService {

    private final RepositoryDataProvider repositoryDataProvider;

    public RepositoryLookerService(final RepositoryDataProvider repositoryDataProvider) {
        this.repositoryDataProvider = repositoryDataProvider;
    }

    public Mono<List<Items>> findRepository(final Date dateTime,
            final String order,
            final String sortBy,
            final String programmingLanguage,
            final int limit) {
        final var filters = prepareFilters(order, sortBy, limit);
        final var results = repositoryDataProvider.searchRepositories(formatDate(dateTime), filters);
        if (programmingLanguage != null) {
            return results.map(result -> result.getItems()
                    .stream()
                    .filter(item -> programmingLanguage.equalsIgnoreCase(item.getLanguage()))
                    .toList());
        }
        return results.map(result -> result.getItems());
    }

    //this can be removed after finding way to pass filters more gracefully.
    private MultiValueMap<String, String> prepareFilters(final String order, final String sortBy, final int limit) {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("sort", sortBy);
        filters.add("per_page", String.valueOf(limit));
        filters.add("order", order);
        return filters;
    }

    //this can be removed after finding way to pass filters more gracefully.
    private String formatDate(final Date dateTime) {
        final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final var instant = dateTime.toInstant();
        LocalDateTime ldt = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ldt.format(formatter);
    }

}
