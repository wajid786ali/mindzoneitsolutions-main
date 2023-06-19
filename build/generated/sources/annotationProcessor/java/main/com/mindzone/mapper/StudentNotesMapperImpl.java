package com.mindzone.mapper;

import com.mindzone.dto.StudentNotesDto;
import com.mindzone.entity.StudentNotes;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T00:01:32-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class StudentNotesMapperImpl implements StudentNotesMapper {

    @Override
    public StudentNotes toEntity(StudentNotesDto d) {
        if ( d == null ) {
            return null;
        }

        StudentNotes.StudentNotesBuilder studentNotes = StudentNotes.builder();

        studentNotes.id( d.getId() );
        studentNotes.studentId( d.getStudentId() );
        studentNotes.studentName( d.getStudentName() );
        studentNotes.comments( d.getComments() );
        studentNotes.resolved( d.getResolved() );
        studentNotes.reminderDate( d.getReminderDate() );
        studentNotes.completedDate( d.getCompletedDate() );
        studentNotes.createdDate( d.getCreatedDate() );

        return studentNotes.build();
    }

    @Override
    public StudentNotesDto toDto(StudentNotes e) {
        if ( e == null ) {
            return null;
        }

        StudentNotesDto.StudentNotesDtoBuilder<?, ?> studentNotesDto = StudentNotesDto.builder();

        studentNotesDto.id( e.getId() );
        studentNotesDto.studentId( e.getStudentId() );
        studentNotesDto.studentName( e.getStudentName() );
        studentNotesDto.comments( e.getComments() );
        studentNotesDto.resolved( e.getResolved() );
        studentNotesDto.reminderDate( e.getReminderDate() );
        studentNotesDto.completedDate( e.getCompletedDate() );
        studentNotesDto.createdDate( e.getCreatedDate() );

        return studentNotesDto.build();
    }
}
