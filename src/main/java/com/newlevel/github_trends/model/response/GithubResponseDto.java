package com.newlevel.github_trends.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubResponseDto {

  private String name;

  private String desc;

  private List<String> contributors;
}
