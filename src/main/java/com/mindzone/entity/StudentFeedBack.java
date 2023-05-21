package com.mindzone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "studentFeedBack")
@Entity
@EqualsAndHashCode
public class StudentFeedBack implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    int noOfWorksheets;
    String studentName;
    private Long studentId;
    String teacherName;
    String comments;
    String worksheetsType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date insertDate;

}
