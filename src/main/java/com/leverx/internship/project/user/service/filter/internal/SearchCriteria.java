package com.leverx.internship.project.user.service.filter.internal;

import javax.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
  private String key;
  private String operation;
  private Object value;

  public boolean isOrPredicate() {
    return this instanceof Predicate;
  }
}