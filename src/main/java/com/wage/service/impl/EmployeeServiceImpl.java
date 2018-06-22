package com.wage.service.impl;

import com.wage.dao.EmployeeMapper;
import com.wage.model.Employee;
import com.wage.service.EmployeeService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: EmployeeService接口实现类
* @author zb
* @date 2018/06/22 14:46
*/
@Service
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

}