package com.leverx.internship.project.project.repository;

import com.leverx.internship.project.project.repository.entity.Project;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository
    extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project> {
  @Query(
      value =
          "select p from Project p "
              + "left join p.employees employees "
              + "where employees.id = ?1 "
              + "and ((p.dateStart between ?2 and ?3 and p.dateEnd between ?2 and ?3) "
              + "or (p.dateStart < ?2 and p.dateEnd > ?3) "
              + "or (p.dateStart > ?2 and p.dateStart < ?3 and p.dateEnd > ?3) "
              + "or (p.dateStart < ?2 and p.dateEnd < ?3 and p.dateEnd > ?2))")
  Collection<Project> findValidProjectsByEmployeesId(
      Integer id, LocalDate dateStart, LocalDate dateEnd);

  @Query("select p from Project p left join p.employees employees where employees.id = ?1 and p.dateEnd > ?2")
  Collection<Project> findProjectsByEmployeesIdAndDateEndAfter(
      Integer idEmployee, LocalDate date);
}
