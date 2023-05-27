package com.mindzone.mapper;

import com.mindzone.dto.StudentNotesDto;
import com.mindzone.entity.StudentNotes;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentNotesMapper
        extends ModelMapperEntityToDto<StudentNotes, StudentNotesDto>, ModelMapperDtoToEntity<StudentNotesDto, StudentNotes> {}
