package com.wage.model;

import javax.persistence.*;

public class Employee {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 工号
     */
    @Column(name = "e_number")
    private String eNumber;

    /**
     * 员工名
     */
    @Column(name = "e_name")
    private String eName;

    /**
     * 性别
     */
    @Column(name = "e_sex")
    private Byte eSex;

    /**
     * 职称
     */
    @Column(name = "e_title")
    private String eTitle;

    /**
     * 身份证号
     */
    @Column(name = "e_ID_card")
    private String eIdCard;

    /**
     * 银行卡号
     */
    @Column(name = "e_bank_card")
    private String eBankCard;

    /**
     * 部门id
     */
    @Column(name = "d_id")
    private Integer dId;

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
     * 获取工号
     *
     * @return e_number - 工号
     */
    public String geteNumber() {
        return eNumber;
    }

    /**
     * 设置工号
     *
     * @param eNumber 工号
     */
    public void seteNumber(String eNumber) {
        this.eNumber = eNumber == null ? null : eNumber.trim();
    }

    /**
     * 获取员工名
     *
     * @return e_name - 员工名
     */
    public String geteName() {
        return eName;
    }

    /**
     * 设置员工名
     *
     * @param eName 员工名
     */
    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    /**
     * 获取性别
     *
     * @return e_sex - 性别
     */
    public Byte geteSex() {
        return eSex;
    }

    /**
     * 设置性别
     *
     * @param eSex 性别
     */
    public void seteSex(Byte eSex) {
        this.eSex = eSex;
    }

    /**
     * 获取职称
     *
     * @return e_title - 职称
     */
    public String geteTitle() {
        return eTitle;
    }

    /**
     * 设置职称
     *
     * @param eTitle 职称
     */
    public void seteTitle(String eTitle) {
        this.eTitle = eTitle == null ? null : eTitle.trim();
    }

    /**
     * 获取身份证号
     *
     * @return e_ID_card - 身份证号
     */
    public String geteIdCard() {
        return eIdCard;
    }

    /**
     * 设置身份证号
     *
     * @param eIdCard 身份证号
     */
    public void seteIdCard(String eIdCard) {
        this.eIdCard = eIdCard == null ? null : eIdCard.trim();
    }

    /**
     * 获取银行卡号
     *
     * @return e_bank_card - 银行卡号
     */
    public String geteBankCard() {
        return eBankCard;
    }

    /**
     * 设置银行卡号
     *
     * @param eBankCard 银行卡号
     */
    public void seteBankCard(String eBankCard) {
        this.eBankCard = eBankCard == null ? null : eBankCard.trim();
    }

    /**
     * 获取部门id
     *
     * @return d_id - 部门id
     */
    public Integer getdId() {
        return dId;
    }

    /**
     * 设置部门id
     *
     * @param dId 部门id
     */
    public void setdId(Integer dId) {
        this.dId = dId;
    }
}