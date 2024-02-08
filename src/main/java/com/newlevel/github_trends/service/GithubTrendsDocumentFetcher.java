package com.newlevel.github_trends.service;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GithubTrendsDocumentFetcher {

  @Value("${github.trends.url}")
  private String githubTrendsUrl;

  public Document fetchGithubTrendsDocument() {
    try {
      log.info("Loading github trends from url {}", githubTrendsUrl);
      return Jsoup.connect(githubTrendsUrl).get();
    } catch (IOException e) {
      log.error("Error occurred while trying to load github trends", e);
      throw new RuntimeException(
          "Error occurred while trying to load github trends from url " + githubTrendsUrl);
    }
  }
}
