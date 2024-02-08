package com.newlevel.github_trends.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import com.newlevel.github_trends.toolkit.Randomizer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class DefaultGithubTrendServiceIntegrationTest {

  @Autowired private GithubTrendService githubTrendService;

  @Test
  public void testGetRepositories() {
    // Run test scenario and verify
    StepVerifier.create(githubTrendService.getRepositories().collectList())
        .expectNextMatches(
            res -> {
              assertThat(res).isNotEmpty();
              return true;
            })
        .verifyComplete();
  }

  @Test
  public void testGetStatistics() {
    // Run test scenario and verify not empty
    StepVerifier.create(githubTrendService.getStatistics().collectList())
        .expectNextMatches(
            res -> {
              assertThat(res).isNotEmpty();
              return true;
            })
        .verifyComplete();
  }

  @Test
  public void testGetByName() {
    var repositoryTrend = Randomizer.generateRandomObject(GithubRepositoryTrendEntity.class);
    repositoryTrend.setId(null);
    githubTrendService.create(repositoryTrend).block();
    StepVerifier.create(githubTrendService.getRepository(repositoryTrend.getName()))
        .expectNextMatches(
            res -> {
              assertThat(res).isNotNull();
              assertThat(res.getDescription()).isEqualTo(repositoryTrend.getDescription());
              assertThat(res.getProgrammingLanguage())
                  .isEqualTo(repositoryTrend.getProgrammingLanguage());
              return true;
            })
        .verifyComplete();
  }
}
