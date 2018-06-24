package com.wage.service.impl;

import com.wage.dao.DeductionMapper;
import com.wage.model.Deduction;
import com.wage.service.DeductionService;
import com.wage.core.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: DeductionService接口实现类
* @author zb
* @date 2018/06/22 14:23
*/
@Service
public class DeductionServiceImpl extends AbstractService<Deduction> implements DeductionService {

    @Resource
    private DeductionMapper deductionMapper;

    @Override
    public List<Deduction> selectTitles() {
        return deductionMapper.selectTitles();
    }

    @Override
    public List<Deduction> selectListByTitleAndState(String title) {
        return deductionMapper.selectListByTitleAndState(title);
    }

    @Override
    public List<Deduction> selectTitlesByState() {
        return deductionMapper.selectTitlesByState();
    }
}