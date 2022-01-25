package com.leverx.internship.project.user.web.controller;

import com.leverx.internship.project.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/")
@AllArgsConstructor
public class UserController {
  private UserRepository userRepository;

  @GetMapping
  public String getUsers(){
    return userRepository.findAll().toString();
  }
}
