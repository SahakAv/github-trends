package com.newlevel.github_trends.model.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshGithubRepositoryTrendModel {

  private String name;

  private String description;

  private String programingLanguage;

  private List<String> primaryContributors;
}
