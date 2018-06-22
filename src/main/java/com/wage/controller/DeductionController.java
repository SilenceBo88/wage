package com.wage.controller;

import com.wage.core.util.Result;
import com.wage.core.util.ResultUtil;
import com.wage.model.Deduction;
import com.wage.service.DeductionService;
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
* @Description: DeductionController类
* @author zb
* @date 2018/06/22 14:23
*/
@RestController
@RequestMapping("/deduction")
public class DeductionController {

    @Resource
    private DeductionService deductionService;

    @PostMapping("/insert")
    public Result<Integer> insert(Deduction deduction) throws Exception{
        Integer state = deductionService.insert(deduction);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = deductionService.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(Deduction deduction) throws Exception {
        Integer state = deductionService.update(deduction);
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<Deduction> selectById(@RequestParam String id) throws Exception {
        Deduction deduction = deductionService.selectById(id);
        return ResultUtil.SUCCESS(deduction);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Deduction>>
    */
    @GetMapping("/list")
    public Result<PageInfo<Deduction>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Deduction> list = deductionService.selectAll();
        PageInfo<Deduction> pageInfo = new PageInfo<Deduction>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}