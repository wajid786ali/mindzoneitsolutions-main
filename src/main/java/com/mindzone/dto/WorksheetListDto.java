package com.mindzone.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class WorksheetListDto {
    List<WorksheetsDto> worksheetsDtoList;
    Set<String> weeklyDate;

}

