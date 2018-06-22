package com.wage.service.impl;

import com.wage.dao.AttendanceMapper;
import com.wage.model.Attendance;
import com.wage.service.AttendanceService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: AttendanceService接口实现类
* @author zb
* @date 2018/06/22 14:23
*/
@Service
public class AttendanceServiceImpl extends AbstractService<Attendance> implements AttendanceService {

    @Resource
    private AttendanceMapper attendanceMapper;

}