package com.leverx.internship.project.user.service.filter;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.filter.internal.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationsBuilder {

  private final List<SearchCriteria> params;

  public UserSpecificationsBuilder() {
    params = new ArrayList<>();
  }

  public UserSpecificationsBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<User> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<User>> specs =
        params.stream().map(UserSpecification::new).collect(Collectors.toList());

    Specification<User> result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result =
          params.get(i).isOrPredicate()
              ? Specification.where(result).or(specs.get(i))
              : Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}