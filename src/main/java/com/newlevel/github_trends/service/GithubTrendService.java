package com.newlevel.github_trends.service;

import com.newlevel.github_trends.model.domain.GithubRepositoryStatisticsDomain;
import com.newlevel.github_trends.model.domain.RefreshGithubRepositoryTrendModel;
import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GithubTrendService {

  Flux<GithubRepositoryTrendEntity> getRepositories();

  Mono<GithubRepositoryTrendEntity> create(GithubRepositoryTrendEntity trendEntity);

  Mono<GithubRepositoryTrendEntity> getRepository(String repoName);

  Flux<GithubRepositoryStatisticsDomain> getStatistics();

  void refresh(List<RefreshGithubRepositoryTrendModel> updatedRepositories);
}
