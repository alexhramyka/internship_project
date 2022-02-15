package com.leverx.internship.project.report.web.controller;

import com.leverx.internship.project.exception.ReportNotFoundException;
import com.leverx.internship.project.report.service.ReportService;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {
  private final ReportService reportService;
  public static final String EXCEL_XLSX_CONTENT_TYPE =
      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  private static final String HEADER_ATTACHMENT = "attachment; filename=Report.xlsx";

  @GetMapping("/workload")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Downloading workload report")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "500", description = "Internal server error")})
  public void downloadWorkloadReport(
      @Parameter(description = "Required month", name = "month")
      @RequestParam(value = "month", required = false) String month, HttpServletResponse response) {
    response.setContentType(EXCEL_XLSX_CONTENT_TYPE);
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, HEADER_ATTACHMENT);
    Workbook workbook = reportService.downloadWorkloadReport(month);
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      workbook.write(outputStream);
      workbook.close();
    } catch (IOException e) {
      throw new ReportNotFoundException(e.getMessage());
    }
  }

  @GetMapping("/available")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Downloading workload report")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
      @ApiResponse(responseCode = "500", description = "Internal server error")})
  public void downloadAvailableEmployeeReport(
      @Parameter(description = "Required month", name = "month")
      @RequestParam(value = "month", required = false) String month, HttpServletResponse response) {
    response.setContentType(EXCEL_XLSX_CONTENT_TYPE);
    response.addHeader(HttpHeaders.CONTENT_DISPOSITION, HEADER_ATTACHMENT);
    Workbook workbook = reportService.downloadAvailableEmployeeReport(month);
    try (ServletOutputStream outputStream = response.getOutputStream()) {
      workbook.write(outputStream);
      workbook.close();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}