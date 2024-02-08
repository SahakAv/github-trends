package com.newlevel.github_trends.model.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("github_repository_primary_contributors")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GithubRepositoryPrimaryContributorEntity extends DomainEntity {

  @Column("username")
  private String username;

  @Column("github_repository_id")
  private UUID githubRepositoryId;
}
