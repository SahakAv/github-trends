package com.newlevel.github_trends.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.newlevel.github_trends.model.domain.GithubRepositoryStatisticsDomain;
import com.newlevel.github_trends.model.entity.GithubRepositoryTrendEntity;
import com.newlevel.github_trends.model.response.GithubResponseDto;
import com.newlevel.github_trends.model.response.GithubTrendsResponseDto;
import com.newlevel.github_trends.model.response.GithubTrendsStatisticsResponseDto;
import com.newlevel.github_trends.toolkit.Randomizer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GithubRepositoryTrendConverterUnitTest {

  @InjectMocks private GithubRepositoryTrendConverter subject;

  @Test
  void testConvertToTrendsResponse() {
    // Mock data
    GithubRepositoryTrendEntity repo1 =
        Randomizer.generateRandomObject(GithubRepositoryTrendEntity.class);

    GithubRepositoryTrendEntity repo2 =
        Randomizer.generateRandomObject(GithubRepositoryTrendEntity.class);

    List<GithubRepositoryTrendEntity> inputRepos = List.of(repo1, repo2);

    // Act
    GithubTrendsResponseDto result = subject.convertToTrendsResponse(inputRepos);

    // Assert
    assertEquals(result.getGithubRepositories().get(0).getName(), repo1.getName());
    assertEquals(result.getGithubRepositories().get(1).getDesc(), repo2.getDescription());
  }

  @Test
  void testConvertToResponse() {
    // Mock data
    GithubRepositoryTrendEntity repo =
        Randomizer.generateRandomObject(GithubRepositoryTrendEntity.class);
    // Act
    GithubResponseDto result = subject.convertToResponse(repo);
    // Assert
    assertEquals(result.getName(), repo.getName());
    assertEquals(result.getDesc(), repo.getDescription());
  }

  @Test
  void testConvertToStatisticsResponse() {
    // Mock data
    GithubRepositoryStatisticsDomain statisticsDomain =
        Randomizer.generateRandomObject(GithubRepositoryStatisticsDomain.class);
    // Act
    GithubTrendsStatisticsResponseDto result =
        subject.convertToStatisticsResponse(List.of(statisticsDomain));
    // Assert
    assertEquals(
        result.getStatistic().get(0).getProgrammingLanguage(),
        statisticsDomain.getProgrammingLanguage());
    assertEquals(result.getStatistic().get(0).getCount(), statisticsDomain.getCount());
  }
}
