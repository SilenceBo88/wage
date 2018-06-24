package com.wage.controller;

import com.wage.core.util.ExcelData;
import com.wage.core.util.ExcelUtil;
import com.wage.model.Attendance;
import com.wage.model.Deduction;
import com.wage.model.Department;
import com.wage.model.Employee;
import com.wage.service.AttendanceService;
import com.wage.service.DeductionService;
import com.wage.service.DepartmentService;
import com.wage.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zb
 * @Date: Created in 2018/6/8 16:32
 * @Description:
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private DeductionService deductionService;

    @RequestMapping("/attendance")
    public void attendance(HttpServletResponse response, String title){
        List<Attendance> list = attendanceService.selectListBy("aTitle", title);
        for (Attendance attendance:list){
            Employee employee = employeeService.selectById(String.valueOf(attendance.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            attendance.setEmployee(employee);
        }

        ExcelData data = new ExcelData();
        data.setName("attendance");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("应出勤次数/月");
        titles.add("实际出勤次数/月");
        titles.add("缺勤次数");
        titles.add("缺勤原因");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Attendance attendance = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(attendance.getaTitle());
            row.add(attendance.getEmployee().geteNumber());
            row.add(attendance.getEmployee().geteName());
            row.add(attendance.getEmployee().getDepartment().getdName());
            row.add(attendance.getaShould());
            row.add(attendance.getaReal());
            row.add(attendance.getaAbsences());
            row.add(attendance.getaAbsencesCause());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtil.exportExcel(response,"出勤情况表",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/deduction")
    public void deduction(HttpServletResponse response, String title){
        List<Deduction> list = deductionService.selectListBy("dTitle", title);
        for (Deduction deduction:list){
            Employee employee = employeeService.selectById(String.valueOf(deduction.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            deduction.setEmployee(employee);
        }

        ExcelData data = new ExcelData();
        data.setName("deduction");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("基本工资");
        titles.add("奖金");
        titles.add("罚金");
        titles.add("税金");
        titles.add("实际工资");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Deduction deduction = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(deduction.getdTitle());
            row.add(deduction.getEmployee().geteNumber());
            row.add(deduction.getEmployee().geteName());
            row.add(deduction.getEmployee().getDepartment().getdName());
            row.add(deduction.getdBasicWage());
            row.add(deduction.getdBonus());
            row.add(deduction.getdFine());
            row.add(deduction.getdTax());
            row.add(deduction.getdRealWage());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtil.exportExcel(response,"工资汇总表",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/bankIssued")
    public void bankIssued(HttpServletResponse response, String title){
        List<Deduction> list = deductionService.selectListByTitleAndState(title);
        for (Deduction deduction:list){
            Employee employee = employeeService.selectById(String.valueOf(deduction.geteId()));
            Department department = departmentService.selectById(String.valueOf(employee.getdId()));
            employee.setDepartment(department);
            deduction.setEmployee(employee);
        }

        ExcelData data = new ExcelData();
        data.setName("bankIssued");

        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("标题");
        titles.add("工号");
        titles.add("姓名");
        titles.add("所属部门");
        titles.add("身份证号");
        titles.add("银行卡号");
        titles.add("实际工资");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            Deduction deduction = list.get(i);
            List<Object> row = new ArrayList();
            row.add(i + 1);
            row.add(deduction.getdTitle());
            row.add(deduction.getEmployee().geteNumber());
            row.add(deduction.getEmployee().geteName());
            row.add(deduction.getEmployee().getDepartment().getdName());
            row.add(deduction.getEmployee().geteIdCard());
            row.add(deduction.getEmployee().geteBankCard());
            row.add(deduction.getdRealWage());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtil.exportExcel(response,"银行发放表",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
