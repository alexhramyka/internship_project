package com.leverx.internship.project.department.repository.entity;

import com.leverx.internship.project.project.repository.entity.Project;
import com.leverx.internship.project.user.repository.entity.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
  @Id
  @SequenceGenerator(sequenceName = "department_id_seq", name = "department_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "created_by")
  private int createdBy;

  @Column(name = "updated_at")
  private LocalDate updatedAt;

  @Column(name = "updated_by")
  private int updatedBy;

  @OneToMany
  @JoinColumn(name = "department_id")
  private List<User> employees;

  @ManyToMany
  @JoinTable(
      name = "department_has_projects",
      joinColumns = @JoinColumn(name = "department_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id"))
  private Set<Project> projects;
}
