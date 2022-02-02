package com.leverx.internship.project.user.web.controller;

import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.dto.UserDto;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @GetMapping
  public List<UserDto> getUsers(@RequestParam(required = false, defaultValue = "3") int size,
                                @RequestParam(required = false, defaultValue = "0") int page,
                                @RequestParam(required = false) String search) {
    return userService.findAll(page, size, search);
  }

  @GetMapping("/{id}")
  public UserDto getUser(@PathVariable("id") int id) {
    return userService.findById(id);
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
