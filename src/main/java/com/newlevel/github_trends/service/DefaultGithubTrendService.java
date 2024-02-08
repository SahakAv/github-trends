package com.newlevel.github_trends.service;

import com.newlevel.github_trends.model.domain.GithubRepositoryStatisticsDomain;
import com.newlevel.github_trends.model.domain.RefreshGithubRepositoryTrendModel;
import com.newlevel.github_trends.model.entity.GithubRepositoryPrimaryContributorEntity;
import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import com.newlevel.github_trends.repository.GithubRepositoryPrimaryContributorRepository;
import com.newlevel.github_trends.repository.GithubTrendRepository;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DefaultGithubTrendService implements GithubTrendService {

  private final GithubTrendRepository githubTrendRepository;

  private final GithubRepositoryPrimaryContributorRepository
      githubRepositoryPrimaryContributorRepository;

  public DefaultGithubTrendService(
      GithubTrendRepository githubTrendRepository,
      GithubRepositoryPrimaryContributorRepository githubRepositoryPrimaryContributorRepository) {
    this.githubTrendRepository = githubTrendRepository;
    this.githubRepositoryPrimaryContributorRepository =
        githubRepositoryPrimaryContributorRepository;
  }

  @Override
  public Flux<GithubRepositoryTrendEntity> getRepositories() {
    return githubTrendRepository.findAll().flatMap(this::enrichWithContributorInfo);
  }

  @Override
  public Mono<GithubRepositoryTrendEntity> create(GithubRepositoryTrendEntity trendEntity) {
    return githubTrendRepository.save(trendEntity);
  }

  @Override
  public Mono<GithubRepositoryTrendEntity> getRepository(String repoName) {
    return githubTrendRepository.findByName(repoName).flatMap(this::enrichWithContributorInfo);
  }

  @Override
  public Flux<GithubRepositoryStatisticsDomain> getStatistics() {
    return githubTrendRepository.getRepoStatistics();
  }

  @Override
  public void refresh(List<RefreshGithubRepositoryTrendModel> updatedRepositories) {
    // We can block it as it's executed from scheduler
    githubTrendRepository.deleteAll().block();
    githubRepositoryPrimaryContributorRepository.deleteAll().block();

    Flux.fromIterable(updatedRepositories)
        .flatMap(
            repo -> {
              var converted =
                  new GithubRepositoryTrendEntity(
                      repo.getName(),
                      repo.getDescription(),
                      repo.getProgramingLanguage(),
                      Collections.emptyList());
              return Mono.zip(
                  githubTrendRepository.save(converted), Mono.just(repo.getPrimaryContributors()));
            })
        .flatMap(
            tuple -> {
              var repositoryId = tuple.getT1().getId();
              var contributors =
                  tuple.getT2().stream()
                      .map(c -> new GithubRepositoryPrimaryContributorEntity(c, repositoryId))
                      .toList();
              return githubRepositoryPrimaryContributorRepository.saveAll(contributors);
            })
        .collectList()
        .block();
  }

  private Mono<GithubRepositoryTrendEntity> enrichWithContributorInfo(
      GithubRepositoryTrendEntity repositoryTrend) {
    return githubRepositoryPrimaryContributorRepository
        .getByGithubRepositoryId(repositoryTrend.getId())
        .collectList()
        .map(
            primaryContributors -> {
              repositoryTrend.setContributors(primaryContributors);
              return repositoryTrend;
            });
  }
}
