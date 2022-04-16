package com.lion.common.controller.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lion.annotation.AuthorizationIgnore;
import com.lion.common.entity.other.EnumUtil;
import com.lion.common.service.other.EnumUtilService;
import com.lion.core.IResultData;
import com.lion.core.LionObjectMapper;
import com.lion.core.ResultData;
import com.lion.core.controller.BaseController;
import com.lion.core.controller.impl.BaseControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author mr.liu
 * @Description: 枚举转selelct下拉框
 * @date 2020/9/15上午11:04
 */
@RestController
@RequestMapping("/enum")
@Api(tags = {"枚举转selelct下拉框"})
public class EnumToSelectController extends BaseControllerImpl implements BaseController {

    @Autowired
    private EnumUtilService enumUtilService;

    @GetMapping("/to/select")
    @AuthorizationIgnore
    @ApiOperation(value = "枚举转selelct下拉框", notes = "枚举转selelct下拉框")
    public IResultData<List<Map<String,String>>> enumToSelect(@NotBlank(message = "请输入enumClass") String enumClass) throws JsonProcessingException {
        Optional<EnumUtil> optional = enumUtilService.find(enumClass);
        if (!optional.isPresent()) {
            return ResultData.instance();
        }
        EnumUtil enumUtil = optional.get();
        LionObjectMapper objectMapper = new LionObjectMapper();
        return ResultData.instance().setData(objectMapper.readValue(enumUtil.getValue(), List.class));
    }

    @PostMapping("/persistence")
    @AuthorizationIgnore
    public void persistence(@RequestBody List<EnumUtil> list){
        list.forEach(enumUtil -> {
            enumUtilService.save(enumUtil);
        });
    }

    @GetMapping("/list")
    @AuthorizationIgnore
    @ApiOperation(value = "获取所有字典定义")
    public IResultData<List<EnumUtil>> list(){
        return ResultData.instance().setData(enumUtilService.findAll());
    }
}
