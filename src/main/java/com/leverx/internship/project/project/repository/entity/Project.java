package com.leverx.internship.project.project.repository.entity;

import com.leverx.internship.project.user.repository.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Project {
  @Id
  @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
  private int id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="description", nullable = false)
  private String description;

  @Column(name="created_at", nullable = false, insertable = false, updatable = false)
  private Date createdAt;

  @Column(name="created_at", nullable = false, insertable = false, updatable = false)
  private Date updatedAt;

  @Column(name="created_by", nullable = false)
  private int createdBy;

  @Column(name="updated_by", nullable = false)
  private int updatedBy;

  @ManyToMany
  @JoinTable(
      name="employee_has_projects",
      joinColumns = @JoinColumn(name="project_id"),
      inverseJoinColumns = @JoinColumn(name="employee_id"))
  @ToString.Exclude
  private List<User> employees;
}
