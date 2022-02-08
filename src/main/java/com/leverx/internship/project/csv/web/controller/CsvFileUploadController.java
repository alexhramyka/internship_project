package com.leverx.internship.project.csv.web.controller;

import com.leverx.internship.project.csv.service.CsvFileService;
import com.leverx.internship.project.user.web.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class CsvFileUploadController {

  private final CsvFileService csvFileService;

  @PostMapping(value = "/upload")
  public List<UserResponse> uploadUsersFromCsv(@RequestBody MultipartFile csvFile) {
    return csvFileService.uploadUsers(csvFile);
  }
}
