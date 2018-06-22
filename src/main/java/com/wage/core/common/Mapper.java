package com.wage.core.common;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @Author: zb
 * @Date: Created in 2018/6/ 21:01
 * @Description: 通用mapper
 */
public interface Mapper<T> extends BaseMapper<T> , ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T> {
}
