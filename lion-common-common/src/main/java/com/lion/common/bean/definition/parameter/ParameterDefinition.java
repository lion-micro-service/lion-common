package com.lion.common.bean.definition.parameter;

import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * @author mr.liu
 * @Description: 系统参数
 * @date 2020/9/14下午4:31
 */
@Data
@Schema(description = "系统参数")
@MappedSuperclass
public class ParameterDefinition extends BaseEntity {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "值")
    private String value;

    @Schema(description = "关联编码")
    private String parentCode = "000";

    @Schema(description = "sort")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
