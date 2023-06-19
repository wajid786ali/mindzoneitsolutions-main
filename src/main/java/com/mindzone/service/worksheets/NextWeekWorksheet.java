package com.mindzone.service.worksheets;

import com.mindzone.dto.WorksheetsDto;

import java.util.List;

public interface NextWeekWorksheet {
   public List<WorksheetsDto> homeworkGenerator(String newWeekDate,String subject);
   
}
