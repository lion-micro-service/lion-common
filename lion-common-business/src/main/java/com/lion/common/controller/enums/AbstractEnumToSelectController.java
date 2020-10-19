package com.lion.common.controller.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lion.annotation.AuthorizationIgnore;
import com.lion.common.entity.other.EnumUtil;
import com.lion.common.service.other.EnumUtilService;
import com.lion.core.IResultData;
import com.lion.core.LionObjectMapper;
import com.lion.core.ResultData;
import com.lion.core.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author mr.liu
 * @Description: 枚举转selelct下拉框
 * @date 2020/9/15上午10:51
 */
public abstract class AbstractEnumToSelectController {

    @Autowired
    private EnumUtilService enumUtilService;

    @GetMapping("/to/select")
    @AuthorizationIgnore
    @ApiOperation(value = "枚举转selelct下拉框", notes = "枚举转selelct下拉框")
    public IResultData<List<Map<String,String>>> enumToSelect(@NotBlank(message = "请输入enumClass") String enumClass) throws JsonProcessingException {
        EnumUtil enumUtil = enumUtilService.find(enumClass);
        if (Objects.nonNull(enumUtil)) {
            LionObjectMapper objectMapper = new LionObjectMapper();
            return ResultData.instance().setData(objectMapper.readValue(enumUtil.getValue(), List.class));
        }
        return ResultData.instance();
    }
}
