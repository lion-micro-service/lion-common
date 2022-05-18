package com.lion.common.controller.parameter;

import com.lion.common.dto.CuParameterDto;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.mapper.ParameterMapper;
import com.lion.common.service.parameter.ParameterService;
import com.lion.constant.SearchConstant;
import com.lion.core.*;
import com.lion.core.controller.BaseController;
import com.lion.core.controller.impl.BaseControllerImpl;
import com.lion.core.persistence.JpqlParameter;
import com.lion.core.persistence.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import com.lion.core.Optional;

/**
 * @author mr.liu
 * @Description: 参数controller
 * @date 2020/9/15上午11:06
 */
@RestController
@RequestMapping("/parameter")
@Validated
@Api(tags = {"系统参数管理"})
public class ParameterController extends BaseControllerImpl implements BaseController {

    @Autowired
    private ParameterService parameterService;

    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')")
    @ApiOperation(value = "列表",notes = "列表")
    public IPageResultData<List<Parameter>> list(LionPage lionPage, String code, String name , Integer sort){
        JpqlParameter jpqlParameter = new JpqlParameter();
        if (StringUtils.hasText(code)){
            jpqlParameter.setSearchParameter(SearchConstant.LIKE+"_code",code);
        }
        if (StringUtils.hasText(name)){
            jpqlParameter.setSearchParameter(SearchConstant.LIKE+"_name",name);
        }
        jpqlParameter.setSortParameter("createDateTime", Sort.Direction.DESC);
        lionPage.setJpqlParameter(jpqlParameter);
        return (PageResultData) this.parameterService.findNavigator(lionPage);
    }

    @GetMapping("/check/code/exist")
    @ApiOperation(value = "检查编码是否存在",notes = "检查编码是否存在")
    public IResultData<Boolean> checkCodeIsExist(@NotBlank(message = "编码不能为空")String code,@ApiParam(value = "修改时需要传,新增时不需要传") Long id){
        return ResultData.instance().setData(parameterService.checkCodeExist(code,id));
    }

    @ApiOperation(value = "新增参数设置",notes = "新增参数设置")
    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_ADD')")
    public IResultData add(@RequestBody @Validated({Validator.Insert.class}) CuParameterDto cuParameterDto){
        parameterService.save(ParameterMapper.INSTANCE.CuParameterDtoToParameter(cuParameterDto));
        return ResultData.instance();
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改参数设置",notes = "修改参数设置")
    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_UPDATE')")
    public IResultData update(@RequestBody @Validated({Validator.Update.class})CuParameterDto cuParameterDto){
        parameterService.update(ParameterMapper.INSTANCE.CuParameterDtoToParameter(cuParameterDto));
        return ResultData.instance();
    }

    @ApiOperation(value = "获取详情",notes = "获取详情")
    @GetMapping("/details")
    public IResultData<Boolean> details(@NotNull(message = "id不能为空")Long id){
        Optional<Parameter> optional = parameterService.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Parameter parameter = optional.get();
        return ResultData.instance().setData(parameter);
    }

    @ApiOperation(value = "删除参数设置",notes = "删除参数设置")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')")
    public IResultData delete(@NotNull(message = "id不能为空") @RequestParam(value = "id",required = false) @ApiParam(value = "数组(id=1&id=2)")  List<Long> id){
        parameterService.deleteByIds(id);
        ResultData resultData = ResultData.instance();
        return resultData;
    }

}
