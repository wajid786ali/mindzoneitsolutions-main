package com.mindzone.mapper;

import com.mindzone.dto.TeachersDto;
import com.mindzone.entity.Teachers;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeachersMapper
        extends ModelMapperEntityToDto<Teachers, TeachersDto>, ModelMapperDtoToEntity<TeachersDto, Teachers> {}

