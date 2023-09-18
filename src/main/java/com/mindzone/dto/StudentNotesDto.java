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
public class StudentNotesDto {
    private UUID id;
    private Long studentId;
    private String studentName;
    private String comments;
    private String resolved;
    private Date reminderDate;
    private Date completedDate;
    private Date createdDate;
    private String center;
}
