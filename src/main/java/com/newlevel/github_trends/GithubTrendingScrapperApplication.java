package com.newlevel.github_trends;

import com.newlevel.github_trends.configuraiton.GithubTrendsPageJsoupConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(GithubTrendsPageJsoupConfiguration.class)
@EnableR2dbcRepositories
public class GithubTrendingScrapperApplication {

  public static void main(String[] args) {
    SpringApplication.run(GithubTrendingScrapperApplication.class, args);
  }
}
