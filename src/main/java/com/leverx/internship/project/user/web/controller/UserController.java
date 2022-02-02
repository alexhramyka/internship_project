package com.leverx.internship.project.user.web.controller;

import com.leverx.internship.project.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("users/")
@AllArgsConstructor
public class UserController {
  private UserRepository userRepository;

  @GetMapping
  public String getUsers(){
    return userRepository.findAll().toString();
  }

  @GetMapping("/{id}")
  public String getUser(@PathVariable("id") @Valid int id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " doesn't exist"))
        .toString();
  }
}
