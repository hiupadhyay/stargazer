package com.tools.stargazer.api;

import java.util.Date;
import java.util.List;

import com.tools.stargazer.client.response.Items;
import com.tools.stargazer.service.RepositoryLookerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RepositoryLookerImpl implements RepositoryLooker<Mono<List<Items>>> {

    private final RepositoryLookerService repositoryLookerService;

    @Autowired
    public RepositoryLookerImpl(final RepositoryLookerService repositoryLookerService) {
        this.repositoryLookerService = repositoryLookerService;
    }

    @Override
    public ResponseEntity<Mono<List<Items>>> find(final Date dateTime,
            final String order,
            final String sortBy,
            final String programmingLanguage,
            final int limit) {
        final var result = repositoryLookerService.findRepository(dateTime, order, sortBy, programmingLanguage, limit);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
