package com.wage.dao;

import com.wage.core.common.Mapper;
import com.wage.model.Deduction;

import java.util.List;

public interface DeductionMapper extends Mapper<Deduction> {
    List<Deduction> selectTitles();

    List<Deduction> selectListByTitleAndState(String title);

    List<Deduction> selectTitlesByState();
}