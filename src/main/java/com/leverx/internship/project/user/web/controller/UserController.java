package com.leverx.internship.project.user.web.controller;

import com.leverx.internship.project.user.service.UserService;
import com.leverx.internship.project.user.web.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Get List of all users with pagination and filter by first name, last name, email from the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Found the users", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
      })
  public List<UserDto> getUsers(
      @Parameter(description = "Number of users shown", example = "size=1")
          @RequestParam(required = false, defaultValue = "3")
          int size,
      @Parameter(description = "Page number", example = "page=1")
          @RequestParam(required = false, defaultValue = "0")
          int page,
      @Parameter(
              description = "Search criteria by which data is filtered",
              example = "search=firstName:Alex")
          @RequestParam(required = false)
          String search) {
    return userService.findAll(page, size, search);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get user by id from the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Found the user", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
      })
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUser(
      @Parameter(description = "Required user id", example = "1") @PathVariable("id") int id) {
    return userService.findById(id);
  }

  @PostMapping
  @Operation(summary = "Save user to the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "203", description = "Created", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
      })
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@Parameter(description = "User information") @RequestBody UserDto userDto) {
    return userService.create(userDto);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Save user to the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
      })
  @ResponseStatus(HttpStatus.OK)
  public UserDto update(
      @Parameter(description = "Required user id", example = "1") @PathVariable("id") int id,
      @Parameter(description = "User information for update") @RequestBody UserDto userDtoUpdate) {
    return userService.update(id, userDtoUpdate);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete user in the database")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "No content"),
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
      })
  @DeleteMapping("/{id}")
  public void delete(
      @Parameter(description = "Required user id", example = "1") @PathVariable("id") int id) {
    userService.delete(id);
  }
}
