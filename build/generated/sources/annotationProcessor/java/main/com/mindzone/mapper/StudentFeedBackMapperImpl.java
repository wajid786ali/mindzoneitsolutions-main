package com.mindzone.mapper;

import com.mindzone.dto.StudentFeedBackDto;
import com.mindzone.entity.StudentFeedBack;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-23T16:49:59-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class StudentFeedBackMapperImpl implements StudentFeedBackMapper {

    @Override
    public StudentFeedBack toEntity(StudentFeedBackDto d) {
        if ( d == null ) {
            return null;
        }

        StudentFeedBack.StudentFeedBackBuilder studentFeedBack = StudentFeedBack.builder();

        if ( d.getNoOfWorksheets() != null ) {
            studentFeedBack.noOfWorksheets( Integer.parseInt( d.getNoOfWorksheets() ) );
        }
        studentFeedBack.studentName( d.getStudentName() );
        if ( d.getStudentId() != null ) {
            studentFeedBack.studentId( Long.parseLong( d.getStudentId() ) );
        }
        studentFeedBack.teacherName( d.getTeacherName() );
        studentFeedBack.comments( d.getComments() );
        studentFeedBack.worksheetsType( d.getWorksheetsType() );
        studentFeedBack.center( d.getCenter() );

        return studentFeedBack.build();
    }

    @Override
    public StudentFeedBackDto toDto(StudentFeedBack e) {
        if ( e == null ) {
            return null;
        }

        StudentFeedBackDto.StudentFeedBackDtoBuilder<?, ?> studentFeedBackDto = StudentFeedBackDto.builder();

        studentFeedBackDto.noOfWorksheets( String.valueOf( e.getNoOfWorksheets() ) );
        studentFeedBackDto.studentName( e.getStudentName() );
        if ( e.getStudentId() != null ) {
            studentFeedBackDto.studentId( String.valueOf( e.getStudentId() ) );
        }
        studentFeedBackDto.teacherName( e.getTeacherName() );
        studentFeedBackDto.comments( e.getComments() );
        studentFeedBackDto.worksheetsType( e.getWorksheetsType() );
        studentFeedBackDto.center( e.getCenter() );

        return studentFeedBackDto.build();
    }
}
