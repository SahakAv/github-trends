# GitHub Trending Scraper

## Overview

The GitHub Trending Scraper is a Java application designed to scrape GitHub trending pages and store the data in a database. It uses web scraping techniques to gather information about trending repositories, such as repository name, description, contributors, etc., and persists the data in a database for further analysis.

## Features

- Web scraping of GitHub trending pages to fetch repository information.
- Database storage of trending repository data.

## Prerequisites

- Java 17 
- Gradle (for building and managing dependencies)
- H2 DB used for in memory db for testing purposed
- Spring Webflux for creating reactive API-s
- Jsop used for parsing github html

# GitHub Trending Scraper API

## Endpoints

### 1. Fetch All Trends

- **Endpoint:** `http://localhost:8080/v1/github/repository/trends`
- **Description:** Fetch all trending repositories.
- **Method:** GET

### 2. Fetch Trend Repo Information by Name

- **Endpoint:** `http://localhost:8080/v1/github/repository?name=danielmiessler/fabric`
- **Description:** Fetch information about a trending repository by its name.
- **Method:** GET

### 3. Fetch Trending Repos Statistics

- **Endpoint:** `http://localhost:8080/v1/github/repository/trends/statistics`
- **Description:** Fetch statistics about trending repositories, including programming language and count.
- **Method:** GET
