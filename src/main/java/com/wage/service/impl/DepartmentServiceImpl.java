package com.wage.service.impl;

import com.wage.dao.DepartmentMapper;
import com.wage.model.Department;
import com.wage.service.DepartmentService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: DepartmentService接口实现类
* @author zb
* @date 2018/06/22 14:21
*/
@Service
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

}