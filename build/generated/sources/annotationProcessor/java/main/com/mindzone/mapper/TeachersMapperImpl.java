package com.mindzone.mapper;

import com.mindzone.dto.TeachersDto;
import com.mindzone.entity.Teachers;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-08T09:57:24-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class TeachersMapperImpl implements TeachersMapper {

    @Override
    public Teachers toEntity(TeachersDto d) {
        if ( d == null ) {
            return null;
        }

        Teachers.TeachersBuilder teachers = Teachers.builder();

        teachers.teacherName( d.getTeacherName() );
        teachers.active( d.isActive() );
        teachers.email( d.getEmail() );
        teachers.password( d.getPassword() );
        teachers.center( d.getCenter() );
        teachers.type( d.getType() );
        teachers.phoneNumber( d.getPhoneNumber() );
        teachers.address( d.getAddress() );
        teachers.startDate( d.getStartDate() );
        teachers.endDate( d.getEndDate() );

        return teachers.build();
    }

    @Override
    public TeachersDto toDto(Teachers e) {
        if ( e == null ) {
            return null;
        }

        TeachersDto.TeachersDtoBuilder<?, ?> teachersDto = TeachersDto.builder();

        teachersDto.teacherName( e.getTeacherName() );
        teachersDto.active( e.isActive() );
        teachersDto.email( e.getEmail() );
        teachersDto.password( e.getPassword() );
        teachersDto.center( e.getCenter() );
        teachersDto.type( e.getType() );
        teachersDto.phoneNumber( e.getPhoneNumber() );
        teachersDto.address( e.getAddress() );
        teachersDto.startDate( e.getStartDate() );
        teachersDto.endDate( e.getEndDate() );

        return teachersDto.build();
    }
}
