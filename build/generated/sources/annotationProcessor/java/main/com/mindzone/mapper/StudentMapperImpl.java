package com.mindzone.mapper;

import com.mindzone.dto.StudentRequestDto;
import com.mindzone.dto.StudentResponseDto;
import com.mindzone.entity.Students;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-27T23:06:39-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Students toEntity(StudentRequestDto d) {
        if ( d == null ) {
            return null;
        }

        Students.StudentsBuilder students = Students.builder();

        students.studentId( d.getStudentId() );
        students.studentName( d.getStudentName() );
        students.parentsName( d.getParentsName() );
        students.address( d.getAddress() );
        students.email( d.getEmail() );
        students.phoneNumber( d.getPhoneNumber() );
        students.grade( d.getGrade() );
        List<String> list = d.getSubjects();
        if ( list != null ) {
            students.subjects( new ArrayList<String>( list ) );
        }
        List<String> list1 = d.getDays();
        if ( list1 != null ) {
            students.days( new ArrayList<String>( list1 ) );
        }
        students.startDate( d.getStartDate() );
        students.endDate( d.getEndDate() );
        if ( d.getStatus() != null ) {
            students.status( d.getStatus().name() );
        }
        students.amount( d.getAmount() );

        return students.build();
    }

    @Override
    public StudentResponseDto toDto(Students e) {
        if ( e == null ) {
            return null;
        }

        StudentResponseDto.StudentResponseDtoBuilder<?, ?> studentResponseDto = StudentResponseDto.builder();

        studentResponseDto.studentId( e.getStudentId() );
        studentResponseDto.studentName( e.getStudentName() );
        studentResponseDto.parentsName( e.getParentsName() );
        studentResponseDto.address( e.getAddress() );
        studentResponseDto.email( e.getEmail() );
        studentResponseDto.phoneNumber( e.getPhoneNumber() );
        studentResponseDto.grade( e.getGrade() );
        List<String> list = e.getSubjects();
        if ( list != null ) {
            studentResponseDto.subjects( new ArrayList<String>( list ) );
        }
        List<String> list1 = e.getDays();
        if ( list1 != null ) {
            studentResponseDto.days( new ArrayList<String>( list1 ) );
        }
        studentResponseDto.startDate( e.getStartDate() );
        studentResponseDto.endDate( e.getEndDate() );
        studentResponseDto.status( e.getStatus() );
        studentResponseDto.amount( e.getAmount() );

        return studentResponseDto.build();
    }
}
