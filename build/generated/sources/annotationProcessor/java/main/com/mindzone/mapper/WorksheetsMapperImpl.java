package com.mindzone.mapper;

import com.mindzone.dto.WorksheetsDto;
import com.mindzone.entity.Worksheets;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-23T16:49:59-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class WorksheetsMapperImpl implements WorksheetsMapper {

    @Override
    public Worksheets toEntity(WorksheetsDto d) {
        if ( d == null ) {
            return null;
        }

        Worksheets.WorksheetsBuilder worksheets = Worksheets.builder();

        worksheets.id( d.getId() );
        worksheets.studentName( d.getStudentName() );
        worksheets.studentId( d.getStudentId() );
        worksheets.grade( d.getGrade() );
        worksheets.subject( d.getSubject() );
        worksheets.worksheet( d.getWorksheet() );
        worksheets.extraWorksheet( d.getExtraWorksheet() );
        worksheets.worksheetPath( d.getWorksheetPath() );
        worksheets.extraWorksheetPath( d.getExtraWorksheetPath() );
        worksheets.worksheetPathAnswer( d.getWorksheetPathAnswer() );
        worksheets.extraWorksheetPathAnswer( d.getExtraWorksheetPathAnswer() );
        worksheets.month( d.getMonth() );
        worksheets.status( d.getStatus() );
        worksheets.message( d.getMessage() );
        worksheets.weekDate( d.getWeekDate() );
        worksheets.center( d.getCenter() );

        return worksheets.build();
    }

    @Override
    public WorksheetsDto toDto(Worksheets e) {
        if ( e == null ) {
            return null;
        }

        WorksheetsDto.WorksheetsDtoBuilder<?, ?> worksheetsDto = WorksheetsDto.builder();

        worksheetsDto.id( e.getId() );
        worksheetsDto.studentName( e.getStudentName() );
        worksheetsDto.studentId( e.getStudentId() );
        worksheetsDto.grade( e.getGrade() );
        worksheetsDto.subject( e.getSubject() );
        worksheetsDto.worksheet( e.getWorksheet() );
        worksheetsDto.worksheetPath( e.getWorksheetPath() );
        worksheetsDto.extraWorksheet( e.getExtraWorksheet() );
        worksheetsDto.extraWorksheetPath( e.getExtraWorksheetPath() );
        worksheetsDto.worksheetPathAnswer( e.getWorksheetPathAnswer() );
        worksheetsDto.extraWorksheetPathAnswer( e.getExtraWorksheetPathAnswer() );
        worksheetsDto.status( e.getStatus() );
        worksheetsDto.message( e.getMessage() );
        worksheetsDto.month( e.getMonth() );
        worksheetsDto.weekDate( e.getWeekDate() );
        worksheetsDto.center( e.getCenter() );

        return worksheetsDto.build();
    }
}
