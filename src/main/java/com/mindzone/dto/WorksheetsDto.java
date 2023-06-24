package com.mindzone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class WorksheetsDto {

    private UUID id;
    String studentName;
    private Long studentId;
    String grade;
    String subject;
    String worksheet;
    String worksheetPath;
    String worksheetStatus;
    String worksheetExtraStatus;
    String worksheetNewSuggested;
    String worksheetNewSuggestedPath;
    String extraWorksheet;
    String extraWorksheetPath;
    String worksheetPathAnswer;
    String extraWorksheetPathAnswer;
    String status;
    String month;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date weekDate;


}
