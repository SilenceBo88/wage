package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Attendance;
import com.wage.service.AttendanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: AttendanceController类
* @author zb
* @date 2018/06/22 14:23
*/
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @PostMapping("/insert")
    public Result<Integer> insert(Attendance attendance) throws Exception{
        Integer state = attendanceService.insert(attendance);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = attendanceService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Attendance attendance) throws Exception {
        Integer state = attendanceService.update(attendance);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Attendance> selectById(@RequestParam String id) throws Exception {
        Attendance attendance = attendanceService.selectById(id);
        return ResultUtil.SUCCESS(attendance);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Attendance>>
    */
    @GetMapping("/list")
    public Result<PageInfo<Attendance>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Attendance> list = attendanceService.selectAll();
        PageInfo<Attendance> pageInfo = new PageInfo<Attendance>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}