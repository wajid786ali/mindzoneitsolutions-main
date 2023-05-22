package com.mindzone.mapper;

import com.mindzone.dto.WorksheetsDto;
import com.mindzone.entity.Worksheets;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorksheetsMapper
        extends ModelMapperEntityToDto<Worksheets, WorksheetsDto>, ModelMapperDtoToEntity<WorksheetsDto, Worksheets> {}
