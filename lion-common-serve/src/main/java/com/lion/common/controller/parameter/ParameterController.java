package com.lion.common.controller.parameter;

import com.lion.common.dto.CuParameterDto;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.mapper.ParameterMapper;
import com.lion.common.service.parameter.ParameterService;
import com.lion.common.vo.ParameterDetailTreeVo;
import com.lion.common.vo.ParameterListVo;
import com.lion.common.vo.ParameterTreeVo;
import com.lion.common.vo.ParameterVo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @GetMapping("checkCodeIsExist")
    @ApiOperation(value = "检查编码是否存在", notes = "检查编码是否存在")
    public IResultData<Boolean> checkCodeIsExist(@NotBlank(message = "编码不能为空") String code, @ApiParam(value = "修改时需要传,新增时不需要传") Long id) {
        return ResultData.instance().setData(parameterService.checkCodeExist(code, id));
    }

    @ApiOperation(value = "获取详情", notes = "获取详情")
    @GetMapping("details")
    public IResultData details(@NotNull(message = "id不能为空") Long id) {
        return parameterService.findById(id);
    }

    @ApiOperation(value = "新增数据字典", notes = "新增数据字典")
    @PostMapping("add")
    public IResultData add(@RequestBody @Validated({Validator.Insert.class}) ParameterVo parameterVo) {
        parameterService.save(parameterVo);
        return ResultData.instance();
    }

    @ApiOperation(value = "删除数据字典(直接关联或者间接关联的都会删除)", notes = "删除数据字典(直接关联或者间接关联的都会删除)")
    @DeleteMapping("deleteById")
    public IResultData deleteById(@NotNull(message = "id不能为空") @RequestParam(value = "id", required = false) Long id) {
        parameterService.deleteById(id);
        return ResultData.instance();
    }

    @PutMapping("update")
    @ApiOperation(value = "修改数据字典", notes = "修改数据字典")
    public IResultData update(@RequestBody @Validated({Validator.Update.class}) ParameterVo parameterVo) {
        parameterService.update(parameterVo);
        return ResultData.instance();
    }

    @GetMapping("/list")
    @ApiOperation(value = "字典列表展示", notes = "字典列表展示")
    public IPageResultData list(LionPage page) {
        return parameterService.list(page);
    }

//    @GetMapping("/list")
////    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')")
//    @ApiOperation(value = "列表", notes = "列表")
//    public IPageResultData<List<ParameterListVo>> list(LionPage lionPage, String code, String name, Integer sort) {
//        JpqlParameter jpqlParameter = new JpqlParameter();
//        if (StringUtils.hasText(code)) {
//            jpqlParameter.setSearchParameter(SearchConstant.LIKE + "_code", code);
//        }
//        if (StringUtils.hasText(name)) {
//            jpqlParameter.setSearchParameter(SearchConstant.LIKE + "_name", name);
//        }
//        if (Objects.nonNull(sort)) {
//            jpqlParameter.setSortParameter("sort", Sort.Direction.DESC);
//        }
//        jpqlParameter.setSortParameter("createDateTime", Sort.Direction.DESC);
//        lionPage.setJpqlParameter(jpqlParameter);
//        Page<Parameter> navigator = parameterService.findNavigator(lionPage);
//        List<Parameter> content = navigator.getContent();
//        List<ParameterListVo> parameterListVos = ParameterMapper.INSTANCE.parameterToParameterListVo(content);
//        parameterListVos.forEach(parameterListVo -> {
//            Optional<Parameter> parentParameter = parameterService.findById(parameterListVo.getParentId());
//            if (parentParameter.isPresent()) parameterListVo.setParent(parentParameter.get());
//        });
//        return new PageResultData<>(parameterListVos, lionPage, navigator.getTotalElements());
//    }

    @GetMapping("/list/tree")
//    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_LIST')")
    @ApiOperation(value = "树形列表", notes = "树形列表")
    public IResultData<List<ParameterTreeVo>> listTree() {
        return ResultData.instance().setData(parameterService.listTree(0L));
    }

    @ApiOperation(value = "获取详情", notes = "获取详情")
    @GetMapping("/detail/tree")
    public IResultData<ParameterDetailTreeVo> detailTree(@NotBlank String code) {
        return ResultData.instance().setData(parameterService.detailTree(code));
    }

//    @ApiOperation(value = "删除参数设置(直接关联或者间接关联的都会删除)", notes = "删除参数设置(直接关联或者间接关联的都会删除)")
//    @DeleteMapping("/delete")
////    @PreAuthorize("hasAuthority('SYSTEM_SETTINGS_PARAMETER_DELETE')")
//    public IResultData delete(@NotNull(message = "id不能为空") @RequestParam(value = "id", required = false) @ApiParam(value = "数组(id=1&id=2)") List<Long> id) {
//        parameterService.deleteByIds(id);
//        ResultData resultData = ResultData.instance();
//        return resultData;
//    }
}
