package ${basePackageController};

import ${basePackage}.core.util.Result;
import ${basePackage}.core.util.ResultUtil;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
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
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/insert")
    public Result<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
        Integer state = ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/deleteById")
    public Result<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return ResultUtil.SUCCESS(state);
    }

    @PostMapping("/update")
    public Result<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultUtil.SUCCESS(state);
    }

    @GetMapping("/selectById")
    public Result<${modelNameUpperCamel}> selectById(@RequestParam String id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return ResultUtil.SUCCESS(${modelNameLowerCamel});
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<${modelNameUpperCamel}>>
    */
    @GetMapping("/list")
    public Result<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return ResultUtil.SUCCESS(pageInfo);
    }
}