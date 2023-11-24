package com.mindzone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WorkSheets")
@Entity
@EqualsAndHashCode
public class Worksheets implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    String studentName;
    private Long studentId;
    String grade;
    String subject;
    String worksheet;
    String extraWorksheet;
    String worksheetPath;
    String extraWorksheetPath;
    String worksheetPathAnswer;
    String extraWorksheetPathAnswer;
    String month;
    String status;
    String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date weekDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ssXXX")
    private Date insertDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ssXXX")
    private Date updateDate;
    private String center;
}
