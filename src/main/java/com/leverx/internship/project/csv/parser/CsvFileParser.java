package com.leverx.internship.project.csv.parser;

import com.leverx.internship.project.csv.model.CsvUser;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class CsvFileParser {

  public List<CsvUser> getUsersFromCsv(MultipartFile csvFile) {
    try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
      List<CsvUser> beans =
          new CsvToBeanBuilder<CsvUser>(reader)
              .withSeparator(',')
              .withType(CsvUser.class)
              .withIgnoreLeadingWhiteSpace(true)
              .withIgnoreEmptyLine(true)
              .build()
              .parse();
      return beans;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

}
