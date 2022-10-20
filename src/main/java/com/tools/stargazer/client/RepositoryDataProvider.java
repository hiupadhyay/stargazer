package com.tools.stargazer.client;

import com.tools.stargazer.client.response.Repository;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface RepositoryDataProvider {

    Mono<Repository> searchRepositories(final String creationDate,final MultiValueMap<String, String> filters);
}
