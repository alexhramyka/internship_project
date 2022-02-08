package com.leverx.internship.project.csv.service;

import com.leverx.internship.project.user.web.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvFileService {
  List<UserResponse> uploadUsers(MultipartFile csvFile);
}
