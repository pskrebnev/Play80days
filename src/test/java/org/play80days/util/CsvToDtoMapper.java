package org.play80days.util;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.play80days.dto.BaseDto;

@Slf4j
public final class CsvToDtoMapper {

  private CsvToDtoMapper() {
  }

  public static Object[] map(
      final Class<? extends BaseDto> clazz, final String fileName, final String id) {
    var settings = new CsvParserSettings();

    settings.getFormat().setLineSeparator("\n");
    settings.trimValues(false);

    var routines = new CsvRoutines(settings);

    try (var reader =
        new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
      ArrayList<BaseDto> testData = new ArrayList<>();

      routines.iterate(clazz, reader)
          .forEach(
              e -> {
                if (e.getTestCaseId().equals(id)) {
                  testData.add(e);
                }
              });

      return testData.toArray();
    } catch (IOException e) {
      log.error("CsvToDtoMapper::map", e);
    }

    throw new NullPointerException("Couldn't provide test data");
  }
}
