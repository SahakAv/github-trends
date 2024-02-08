package com.newlevel.github_trends.repository;

import com.newlevel.github_trends.model.domain.GithubRepositoryStatisticsDomain;
import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GithubTrendRepository
    extends ReactiveCrudRepository<GithubRepositoryTrendEntity, UUID> {

  Mono<GithubRepositoryTrendEntity> findByName(String name);

  @Query(
      value =
          "SELECT g.programmingLanguage as programmingLanguage, COUNT(*) as count  FROM github_repository_trends g group by g.programmingLanguage")
  Flux<GithubRepositoryStatisticsDomain> getRepoStatistics();
}
