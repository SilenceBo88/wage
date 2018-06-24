package com.wage.dao;

import com.wage.core.common.Mapper;
import com.wage.model.Attendance;

import java.util.List;

public interface AttendanceMapper extends Mapper<Attendance> {
    List<Attendance> selectTitles();
}