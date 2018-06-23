package com.wage.service.impl;

import com.wage.dao.AdminMapper;
import com.wage.model.Admin;
import com.wage.service.AdminService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: AdminService接口实现类
* @author zb
* @date 2018/06/23 18:01
*/
@Service
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

}