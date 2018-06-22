package com.wage.service.impl;

import com.wage.dao.BankissuedMapper;
import com.wage.model.Bankissued;
import com.wage.service.BankissuedService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: BankissuedService接口实现类
* @author zb
* @date 2018/06/22 14:24
*/
@Service
public class BankissuedServiceImpl extends AbstractService<Bankissued> implements BankissuedService {

    @Resource
    private BankissuedMapper bankissuedMapper;

}