package com.newlevel.github_trends.repository;

import com.newlevel.github_trends.model.entity.GithubRepositoryPrimaryContributorEntity;
import java.util.UUID;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GithubRepositoryPrimaryContributorRepository
    extends ReactiveCrudRepository<GithubRepositoryPrimaryContributorEntity, UUID> {

  Flux<GithubRepositoryPrimaryContributorEntity> getByGithubRepositoryId(
      @Param("repositoryId") UUID repositoryId);
}
