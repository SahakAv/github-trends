package com.newlevel.github_trends.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubRepositoryProgrammingLanguageStatisticResponse {

  private String programmingLanguage;

  private Integer count;
}
