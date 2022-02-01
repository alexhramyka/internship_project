package com.leverx.internship.project.user.web.controller;

import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @GetMapping
  public String getUsers(@RequestParam(required = false, defaultValue = "3") int size,
                         @RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false) String search){
    return userService.findAll(page, size, search).toString();
  }

  @GetMapping("/{id}")
  public String getUser(@PathVariable("id") int id) {
    return userService.findById(id).toString();
  }

  @PostMapping
  public String create(@RequestBody UserDto userDto) {
    return userService.create(userDto);
  }

  @PatchMapping("/{id}")
  public String update(@PathVariable("id") int id,
                       @RequestBody Map<String, Object> values) {
    return userService.update(id, values);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    return userService.delete(id);
  }
}
