package com.mindzone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class WorksheetsDto {

    String studentName;
    private Long studentId;
    String grade;
    String worksheet;
    String extraWorksheet;
    String month;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date weekDate;


}
