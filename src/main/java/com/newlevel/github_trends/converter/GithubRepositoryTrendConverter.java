package com.newlevel.github_trends.converter;

import com.newlevel.github_trends.model.domain.GithubRepositoryStatisticsDomain;
import com.newlevel.github_trends.model.entity.GithubRepositoryPrimaryContributorEntity;
import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import com.newlevel.github_trends.model.response.GithubRepositoryProgrammingLanguageStatisticResponse;
import com.newlevel.github_trends.model.response.GithubResponseDto;
import com.newlevel.github_trends.model.response.GithubTrendsResponseDto;
import com.newlevel.github_trends.model.response.GithubTrendsStatisticsResponseDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryTrendConverter {

  public GithubTrendsResponseDto convertToTrendsResponse(List<GithubRepositoryTrendEntity> repos) {
    var response =
        repos.stream()
            .map(
                r ->
                    new GithubResponseDto(
                        r.getName(),
                        r.getDescription(),
                        r.getContributors().stream()
                            .map(GithubRepositoryPrimaryContributorEntity::getUsername)
                            .toList()))
            .toList();
    return new GithubTrendsResponseDto(response);
  }

  public GithubResponseDto convertToResponse(GithubRepositoryTrendEntity repo) {
    return new GithubResponseDto(
        repo.getName(),
        repo.getDescription(),
        repo.getContributors().stream()
            .map(GithubRepositoryPrimaryContributorEntity::getUsername)
            .toList());
  }

  public GithubTrendsStatisticsResponseDto convertToStatisticsResponse(
      List<GithubRepositoryStatisticsDomain> statisticsDomains) {
    var statisticResponses =
        statisticsDomains.stream()
            .map(
                r ->
                    new GithubRepositoryProgrammingLanguageStatisticResponse(
                        r.getProgrammingLanguage(), r.getCount()))
            .toList();
    return new GithubTrendsStatisticsResponseDto(statisticResponses);
  }
}
