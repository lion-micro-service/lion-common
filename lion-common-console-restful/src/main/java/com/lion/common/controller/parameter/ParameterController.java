package com.lion.common.controller.parameter;

import com.lion.common.entity.parameter.Parameter;
import com.lion.common.service.parameter.ParameterService;
import com.lion.constant.SearchConstant;
import com.lion.core.IResultData;
import com.lion.core.LionPage;
import com.lion.core.ResultData;
import com.lion.core.controller.BaseController;
import com.lion.core.controller.impl.BaseControllerImpl;
import com.lion.core.persistence.JpqlParameter;
import com.lion.core.persistence.Validator;
import org.omg.CORBA.IRObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author mr.liu
 * @Description: 参数controller
 * @date 2020/9/15上午11:06
 */
@RestController
@RequestMapping("/common/parameter/console")
@Validated
public class ParameterController extends BaseControllerImpl implements BaseController {

    @Autowired
    private ParameterService parameterService;

    /**
     * 列表
     * @param lionPage
     * @param code
     * @param name
     * @return
     */
    @GetMapping("/list")
    public IResultData list(LionPage lionPage, String code, String name){
        JpqlParameter jpqlParameter = new JpqlParameter();
        if (StringUtils.hasText(code)){
            jpqlParameter.setSearchParameter(SearchConstant.LIKE+"_code",code);
        }
        if (StringUtils.hasText(name)){
            jpqlParameter.setSearchParameter(SearchConstant.LIKE+"_name",name);
        }
        jpqlParameter.setSortParameter("createDateTime", Sort.Direction.DESC);
        lionPage.setJpqlParameter(jpqlParameter);
        return (IResultData) this.parameterService.findNavigator(lionPage);
    }

    /**
     * 检查编码是否存在
     * @param code
     * @return
     */
    @GetMapping("/check/code/exist")
    public IResultData checkCodeIsExist(@NotBlank(message = "编码不能为空")String code, Long id){
        return ResultData.instance().setData("isExist",parameterService.checkCodeExist(code,id));
    }

    /**
     * 新增参数设置
     * @param parameter
     * @return
     */
    @PostMapping("/add")
    public IResultData add(@RequestBody @Validated({Validator.Insert.class})Parameter parameter){
        parameterService.save(parameter);
        return ResultData.instance();
    }

    /**
     * 修改参数设置
     * @param parameter
     * @return
     */
    @PutMapping("/update")
    public IResultData update(@RequestBody @Validated({Validator.Update.class})Parameter parameter){
        parameterService.update(parameter);
        return ResultData.instance();
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    @GetMapping("/details")
    public IResultData details(@NotNull(message = "id不能为空")Long id){
        return ResultData.instance().setData("parameter",parameterService.findById(id));
    }

    /**
     * 删除参数设置
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public IResultData delete(@NotNull(message = "id不能为空") @RequestParam(value = "id",required = false) List<Long> id){
        id.forEach(i->{
            parameterService.deleteById(i);
        });
        ResultData resultData = ResultData.instance();
        return resultData;
    }

}
