DROP table if exists github_repository_primary_contributors;
DROP table if exists github_repository_trends;

CREATE TABLE IF NOT EXISTS github_repository_trends (
                                                        id UUID default  random_uuid() PRIMARY KEY,
                                                        name VARCHAR(255),
    description VARCHAR(2055),
    programmingLanguage VARCHAR(255)
    );


CREATE TABLE IF NOT EXISTS github_repository_primary_contributors (
                                                    id UUID default  random_uuid()  PRIMARY KEY,
                                                    github_repository_id UUID,
                                                    username VARCHAR(255),
    FOREIGN KEY (github_repository_id) REFERENCES github_repository_trends(id)
    );