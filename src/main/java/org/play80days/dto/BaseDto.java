package org.play80days.dto;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseDto {

  @Parsed(field = "Test Case ID", defaultNullRead = "")
  private String testCaseId;
}
