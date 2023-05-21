package com.mindzone.mapper;


import com.mindzone.dto.StudentFeedBackDto;
import com.mindzone.entity.StudentFeedBack;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentFeedBackMapper
        extends ModelMapperEntityToDto<StudentFeedBack, StudentFeedBackDto>, ModelMapperDtoToEntity<StudentFeedBackDto, StudentFeedBack> {}
