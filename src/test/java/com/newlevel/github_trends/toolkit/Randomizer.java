package com.newlevel.github_trends.toolkit;

import org.jeasy.random.EasyRandom;

public class Randomizer {

  public static <T> T generateRandomObject(Class<T> type) {
    return new EasyRandom().nextObject(type);
  }
}
