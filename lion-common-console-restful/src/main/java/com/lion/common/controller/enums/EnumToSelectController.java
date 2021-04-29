package com.lion.common.controller.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lion.annotation.AuthorizationIgnore;
import com.lion.annotation.SystemLog;
import com.lion.common.entity.other.EnumUtil;
import com.lion.common.service.other.EnumUtilService;
import com.lion.core.IResultData;
import com.lion.core.LionObjectMapper;
import com.lion.core.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author mr.liu
 * @Description: 枚举转selelct下拉框
 * @date 2020/9/15上午11:04
 */
@RestController
@RequestMapping("/enum/console")
@Api(tags = {"枚举转selelct下拉框"})
public class EnumToSelectController extends AbstractEnumToSelectController {

    @Autowired
    private EnumUtilService enumUtilService;

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
