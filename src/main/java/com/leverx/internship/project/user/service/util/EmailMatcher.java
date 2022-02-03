package com.leverx.internship.project.user.service.util;

import com.leverx.internship.project.user.service.filter.UserSpecificationsBuilder;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class EmailMatcher {
  private static final String EMAIL_PATTERN =
      "(\\w+?)(:|<|>)(([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}),";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  private static Matcher emailMatcher;

  public static void buildSpec(UserSpecificationsBuilder builder, String matchString) {
    emailMatcher = pattern.matcher(matchString + ", ");
    while (emailMatcher.find()) {
      builder.with(emailMatcher.group(1), emailMatcher.group(2), emailMatcher.group(3));
    }
  }
}
