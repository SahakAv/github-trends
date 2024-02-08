package com.newlevel.github_trends.service;

import com.newlevel.github_trends.configuraiton.GithubTrendsPageJsoupConfiguration;
import com.newlevel.github_trends.model.domain.RefreshGithubRepositoryTrendModel;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GithubTrendsFetcher {

  private final GithubTrendsDocumentFetcher githubTrendsFetcher;

  private final GithubTrendsPageJsoupConfiguration jsoupConfiguration;

  public GithubTrendsFetcher(
      GithubTrendsDocumentFetcher githubTrendsFetcher,
      GithubTrendsPageJsoupConfiguration jsoupConfiguration) {
    this.githubTrendsFetcher = githubTrendsFetcher;
    this.jsoupConfiguration = jsoupConfiguration;
  }

  public List<RefreshGithubRepositoryTrendModel> getGithubTrends() {
    var trendsDocument = githubTrendsFetcher.fetchGithubTrendsDocument();
    var githubRepositoryDomains = parseTrendsDocument(trendsDocument);
    log.info("Parsed github repositories from trends - {}", githubRepositoryDomains);
    return githubRepositoryDomains;
  }

  private List<RefreshGithubRepositoryTrendModel> parseTrendsDocument(Document document) {
    var repositoriesElement = document.select(jsoupConfiguration.getRepositoriesNode());
    return repositoriesElement.stream()
        .map(
            repoDocument -> {
              var title = repoDocument.select(jsoupConfiguration.getTitle()).text();
              var description = repoDocument.select(jsoupConfiguration.getDescription()).text();
              var language =
                  repoDocument.select(jsoupConfiguration.getProgrammingLanguage()).text();
              var contributorsElement = repoDocument.select(jsoupConfiguration.getContributors());
              var contributors =
                  contributorsElement.stream()
                      .map(
                          contributor ->
                              contributor.select("img.avatar").attr("alt").replace("@", ""))
                      .filter(s -> !s.isBlank())
                      .toList();
              return convertToDomain(title, description, language, contributors);
            })
        .toList();
  }

  private RefreshGithubRepositoryTrendModel convertToDomain(
      String tittle, String desc, String programmingLanguage, List<String> contributors) {
    return new RefreshGithubRepositoryTrendModel(tittle, desc, programmingLanguage, contributors);
  }
}
