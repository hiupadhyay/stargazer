package com.tools.stargazer.client;

import com.tools.stargazer.client.response.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GitClient implements RepositoryDataProvider {
    //can be moved to config or yaml files.
    private static final String BASE_URI = "https://api.github.com/";
    private static final String SEARCH_REPO_ENDPOINT = "/search/repositories";

    private final WebClient webClient;

    @Autowired
    public GitClient(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URI).build();
    }

    /**
     * *
     *
     * @param creationDate mandatory filter
     * @param filters      other filters
     *
     * @return
     */
    @Override
    public Mono<Repository> searchRepositories(final String creationDate, final MultiValueMap<String, String> filters) {

        return this.webClient.get()
                .uri(builder -> builder.path(SEARCH_REPO_ENDPOINT)
                        .queryParam("q", "created:>" + creationDate)
                        .queryParams(filters)
                        .build())
                .header("accept", "application/vnd.github+json")
                .retrieve()
                .bodyToMono(Repository.class)
                .log();

    }

}
