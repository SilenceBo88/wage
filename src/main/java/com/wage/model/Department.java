package com.wage.model;

import javax.persistence.*;

public class Department {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 部门名
     */
    @Column(name = "d_name")
    private String dName;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名
     *
     * @return d_name - 部门名
     */
    public String getdName() {
        return dName;
    }

    /**
     * 设置部门名
     *
     * @param dName 部门名
     */
    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }
}