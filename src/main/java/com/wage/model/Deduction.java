package com.wage.model;

import javax.persistence.*;

public class Deduction {
    /**
     * 主键
     */
    @Id
    private Integer id;

    @Column(name = "d_title")
    private String dTitle;

    /**
     * 基本工资
     */
    @Column(name = "d_basic_wage")
    private Integer dBasicWage;

    /**
     * 奖金
     */
    @Column(name = "d_bonus")
    private Integer dBonus;

    /**
     * 罚金
     */
    @Column(name = "d_fine")
    private Integer dFine;

    /**
     * 税金
     */
    @Column(name = "d_tax")
    private Integer dTax;

    /**
     * 实际工资
     */
    @Column(name = "d_real_wage")
    private Integer dRealWage;

    /**
     * 是否确认
     */
    @Column(name = "d_state")
    private Integer dState;

    /**
     * 员工id
     */
    @Column(name = "e_id")
    private Integer eId;

    private Employee employee;

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

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    /**
     * 获取基本工资
     *
     * @return d_basic_wage - 基本工资
     */
    public Integer getdBasicWage() {
        return dBasicWage;
    }

    /**
     * 设置基本工资
     *
     * @param dBasicWage 基本工资
     */
    public void setdBasicWage(Integer dBasicWage) {
        this.dBasicWage = dBasicWage;
    }

    /**
     * 获取奖金
     *
     * @return d_bonus - 奖金
     */
    public Integer getdBonus() {
        return dBonus;
    }

    /**
     * 设置奖金
     *
     * @param dBonus 奖金
     */
    public void setdBonus(Integer dBonus) {
        this.dBonus = dBonus;
    }

    /**
     * 获取罚金
     *
     * @return d_fine - 罚金
     */
    public Integer getdFine() {
        return dFine;
    }

    /**
     * 设置罚金
     *
     * @param dFine 罚金
     */
    public void setdFine(Integer dFine) {
        this.dFine = dFine;
    }

    /**
     * 获取税金
     *
     * @return d_tax - 税金
     */
    public Integer getdTax() {
        return dTax;
    }

    /**
     * 设置税金
     *
     * @param dTax 税金
     */
    public void setdTax(Integer dTax) {
        this.dTax = dTax;
    }

    /**
     * 获取实际工资
     *
     * @return d_real_wage - 实际工资
     */
    public Integer getdRealWage() {
        return dRealWage;
    }

    /**
     * 设置实际工资
     *
     * @param dRealWage 实际工资
     */
    public void setdRealWage(Integer dRealWage) {
        this.dRealWage = dRealWage;
    }

    public Integer getdState() {
        return dState;
    }

    public void setdState(Integer dState) {
        this.dState = dState;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}