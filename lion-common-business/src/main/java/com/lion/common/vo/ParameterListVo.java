package com.lion.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.common.entity.parameter.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zvoon
 * @date 2022/5/19 11:42
 * @description
 */
@Data
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"isDelete", "createDateTime", "updateDateTime", "createUserId", "updateUserId", "tenantId","parentId"}
)
@Schema(name = "词典列表模型")
public class ParameterListVo extends Parameter {

    @Schema(description = "关联对象")
    private Parameter parent;

}
