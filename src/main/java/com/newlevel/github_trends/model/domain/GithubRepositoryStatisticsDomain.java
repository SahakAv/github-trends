package com.newlevel.github_trends.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubRepositoryStatisticsDomain {

  private String programmingLanguage;
  private Integer count;
}
