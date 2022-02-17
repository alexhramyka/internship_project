package com.leverx.internship.project.web.controllers;

import com.leverx.internship.project.report.job.WorkloadJob;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
  private final WorkloadJob workloadJob;

  @GetMapping("/hello")
  public void hello() {
    workloadJob.createWorkloadReport();
  }
}