package com.lion.common.bean.definition.parameter;

import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
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

    @Column(name = "code")
    @Schema(description = "编码")
    private String code;

    @Column(name = "name")
    @Schema(description = "名称")
    private String name;

    @Schema(description = "值")
    private String value;

    @Column(name = "parent_id")
    @Schema(description = "父id")
    private Long parentId = 0L ;

    @Column(name = "sort")
    @Schema(description = "sort")
    private Integer sort = 0;

    @Schema(description = "备注")
    private String remark;
}
