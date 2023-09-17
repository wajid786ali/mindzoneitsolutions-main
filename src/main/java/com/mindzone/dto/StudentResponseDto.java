package com.mindzone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindzone.utils.StatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public class StudentResponseDto {

    private Long studentId;
    private String studentName;
    private String parentsName;
    private String address;
    private String email;
    private String phoneNumber;
    private String grade;
    private List<String> subjects;
    private List<String> days;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    private String status;
    private String center;
    private BigDecimal amount;
}
