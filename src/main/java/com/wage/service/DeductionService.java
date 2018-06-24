package com.wage.service;

import com.wage.model.Deduction;
import com.wage.core.common.Service;

import java.util.List;

/**
* @Description: DeductionService接口
* @author zb
* @date 2018/06/22 14:23
*/
public interface DeductionService extends Service<Deduction> {

    List<Deduction> selectTitles();

    List<Deduction> selectListByTitleAndState(String title);

    List<Deduction> selectTitlesByState();
}