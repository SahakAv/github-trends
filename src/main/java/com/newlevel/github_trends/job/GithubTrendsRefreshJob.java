package com.newlevel.github_trends.job;

import com.newlevel.github_trends.handler.GithubTrendsRefreshHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component()
@Lazy(value = false)
@Slf4j
public class GithubTrendsRefreshJob {

  private final GithubTrendsRefreshHandler githubTrendsRefreshHandler;

  public GithubTrendsRefreshJob(GithubTrendsRefreshHandler githubTrendsRefreshHandler) {
    this.githubTrendsRefreshHandler = githubTrendsRefreshHandler;
  }

  //    @Scheduled(cron = "* * 1 * * * ")
  @EventListener(ApplicationStartedEvent.class)
  public void refresh() {
    var start = System.currentTimeMillis();
    log.info("Starting github trends refresh");
    githubTrendsRefreshHandler.refreshTrends();
    var end = System.currentTimeMillis();
    log.info("Github trends refresh took - {} millis", end - start);
  }
}
