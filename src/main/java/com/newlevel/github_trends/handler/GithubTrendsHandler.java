package com.newlevel.github_trends.handler;

import com.newlevel.github_trends.converter.GithubRepositoryTrendConverter;
import com.newlevel.github_trends.service.GithubTrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GithubTrendsHandler {

  private final GithubTrendService trendService;

  private final GithubRepositoryTrendConverter converter;

  private static final String REPO_NAME_QUERY_PARAM = "name";

  public GithubTrendsHandler(
      GithubTrendService trendService, GithubRepositoryTrendConverter converter) {
    this.trendService = trendService;
    this.converter = converter;
  }

  public Mono<ServerResponse> getTrends(ServerRequest serverRequest) {
    log.debug("Requested to get all github trends");
    return trendService
        .getRepositories()
        .collectList()
        .map(converter::convertToTrendsResponse)
        .flatMap(resp -> ServerResponse.ok().bodyValue(resp));
  }

  public Mono<ServerResponse> getRepository(ServerRequest serverRequest) {
    var repoName = serverRequest.queryParam(REPO_NAME_QUERY_PARAM);
    log.debug("Requested to get github repo by name - {}", repoName);
    return repoName
        .map(
            string ->
                Mono.just(string)
                    .flatMap(trendService::getRepository)
                    .map(converter::convertToResponse)
                    .flatMap(githubResponseDto -> ServerResponse.ok().bodyValue(githubResponseDto))
                    .switchIfEmpty(ServerResponse.notFound().build()))
        .orElseGet(() -> ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> getTrendsStatistics(ServerRequest serverRequest) {
    log.debug("Requested to get trends statistics");
    return trendService
        .getStatistics()
        .collectList()
        .map(converter::convertToStatisticsResponse)
        .flatMap(response -> ServerResponse.ok().bodyValue(response));
  }
}
