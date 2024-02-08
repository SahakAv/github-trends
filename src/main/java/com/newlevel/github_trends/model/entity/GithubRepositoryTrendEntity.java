package com.newlevel.github_trends.model.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("github_repository_trends")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepositoryTrendEntity extends DomainEntity {

  @Column("name")
  private String name;

  @Column("description")
  private String description;

  @Column("programmingLanguage")
  private String programmingLanguage;

  @Transient private List<GithubRepositoryPrimaryContributorEntity> contributors;
}
