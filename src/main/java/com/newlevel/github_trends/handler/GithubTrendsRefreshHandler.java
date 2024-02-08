package com.newlevel.github_trends.handler;

import com.newlevel.github_trends.service.GithubTrendService;
import com.newlevel.github_trends.service.GithubTrendsFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GithubTrendsRefreshHandler {

  private final GithubTrendService githubTrendService;

  private final GithubTrendsFetcher githubTrendsFetcher;

  public GithubTrendsRefreshHandler(
      GithubTrendService githubTrendService, GithubTrendsFetcher githubTrendsFetcher) {
    this.githubTrendService = githubTrendService;
    this.githubTrendsFetcher = githubTrendsFetcher;
  }

  public void refreshTrends() {
    log.info("Requested to refresh github trends");
    var refreshModels = githubTrendsFetcher.getGithubTrends();
    log.info("Got new github trends {}", refreshModels);
    githubTrendService.refresh(refreshModels);
    log.info("Github trends refreshed");
  }
}
