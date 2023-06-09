package com.mindzone.mapper;

import com.mindzone.dto.WorksheetsDto;
import com.mindzone.entity.Worksheets;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

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
        worksheets.weekDate( d.getWeekDate() );

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
        worksheetsDto.month( e.getMonth() );
        worksheetsDto.weekDate( e.getWeekDate() );

        return worksheetsDto.build();
    }
}
