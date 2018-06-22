package com.wage.model;

import javax.persistence.*;

public class Bankissued {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 实际发放工资
     */
    @Column(name = "b_real_wage")
    private Integer bRealWage;

    /**
     * 员工id
     */
    @Column(name = "e_id")
    private Integer eId;

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
     * 获取实际发放工资
     *
     * @return b_real_wage - 实际发放工资
     */
    public Integer getbRealWage() {
        return bRealWage;
    }

    /**
     * 设置实际发放工资
     *
     * @param bRealWage 实际发放工资
     */
    public void setbRealWage(Integer bRealWage) {
        this.bRealWage = bRealWage;
    }

    /**
     * 获取员工id
     *
     * @return e_id - 员工id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * 设置员工id
     *
     * @param eId 员工id
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }
}