package com.newlevel.github_trends.configuraiton;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "github.trends.jsoup")
@Data
public class GithubTrendsPageJsoupConfiguration {
  private String repositoriesNode;

  private String title;

  private String description;

  private String programmingLanguage;

  private String contributors;
}
