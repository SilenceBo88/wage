package com.wage.model;

import javax.persistence.*;

public class Attendance {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 应出勤次数/月
     */
    @Column(name = "a_should")
    private Integer aShould;

    /**
     * 实际出勤次数/月
     */
    @Column(name = "a_real")
    private Integer aReal;

    /**
     * 缺勤次数
     */
    @Column(name = "a_absences")
    private Integer aAbsences;

    /**
     * 缺勤原因
     */
    @Column(name = "a_absences_cause")
    private String aAbsencesCause;

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
     * 获取应出勤次数/月
     *
     * @return a_should - 应出勤次数/月
     */
    public Integer getaShould() {
        return aShould;
    }

    /**
     * 设置应出勤次数/月
     *
     * @param aShould 应出勤次数/月
     */
    public void setaShould(Integer aShould) {
        this.aShould = aShould;
    }

    /**
     * 获取实际出勤次数/月
     *
     * @return a_real - 实际出勤次数/月
     */
    public Integer getaReal() {
        return aReal;
    }

    /**
     * 设置实际出勤次数/月
     *
     * @param aReal 实际出勤次数/月
     */
    public void setaReal(Integer aReal) {
        this.aReal = aReal;
    }

    /**
     * 获取缺勤次数
     *
     * @return a_absences - 缺勤次数
     */
    public Integer getaAbsences() {
        return aAbsences;
    }

    /**
     * 设置缺勤次数
     *
     * @param aAbsences 缺勤次数
     */
    public void setaAbsences(Integer aAbsences) {
        this.aAbsences = aAbsences;
    }

    /**
     * 获取缺勤原因
     *
     * @return a_absences_cause - 缺勤原因
     */
    public String getaAbsencesCause() {
        return aAbsencesCause;
    }

    /**
     * 设置缺勤原因
     *
     * @param aAbsencesCause 缺勤原因
     */
    public void setaAbsencesCause(String aAbsencesCause) {
        this.aAbsencesCause = aAbsencesCause == null ? null : aAbsencesCause.trim();
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