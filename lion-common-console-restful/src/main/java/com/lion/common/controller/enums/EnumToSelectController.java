package com.lion.common.controller.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lion.annotation.AuthorizationIgnore;
import com.lion.annotation.SystemLog;
import com.lion.common.entity.other.EnumUtil;
import com.lion.common.service.other.EnumUtilService;
import com.lion.core.IResultData;
import com.lion.core.LionObjectMapper;
import com.lion.core.ResultData;
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
@RequestMapping("/common/enum/console")
public class EnumToSelectController extends AbstractEnumToSelectController {

    @Autowired
    private EnumUtilService enumUtilService;

    @PostMapping("/persistence")
    @AuthorizationIgnore
    public IResultData persistence(@RequestBody List<EnumUtil> list){
        list.forEach(enumUtil -> {
            enumUtilService.save(enumUtil);
        });
        return ResultData.instance();
    }
}
