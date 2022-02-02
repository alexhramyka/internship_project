package com.leverx.internship.project.user.service.filter;

import com.leverx.internship.project.user.repository.entity.User;
import com.leverx.internship.project.user.service.filter.internal.SearchCriteria;
import javax.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
@NoArgsConstructor
public class UserSpecification implements Specification<User> {

  private SearchCriteria criteria;

  @Override
  public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}
