package com.newlevel.github_trends.configuraiton;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.newlevel.github_trends.handler.GithubTrendsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class EndpointConfiguration {

  @Bean
  RouterFunction<ServerResponse> getGithubTrends(GithubTrendsHandler handler) {

    return route()
        .GET("/v1/github/repository/trends", handler::getTrends)
        .GET("/v1/github/repository/trends/statistics", handler::getTrendsStatistics)
        .GET("/v1/github/repository", handler::getRepository)
        .build();
  }
}
