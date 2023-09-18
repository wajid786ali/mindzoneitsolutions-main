package com.mindzone.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class StudentFeedBackDto {

    String noOfWorksheets;
    String studentName;
    String  studentId;
    String teacherName;
    String comments;
    String worksheetsType;
     String center;

}
