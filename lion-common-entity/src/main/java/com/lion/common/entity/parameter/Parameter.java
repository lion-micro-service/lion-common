package com.lion.common.entity.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.core.persistence.Validator;
import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author mr.liu
 * @Description: 系统参数
 * @date 2020/9/14下午4:31
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_parameter",indexes = {@Index(columnList = "create_date_time") })

@DynamicInsert
@Data
@Schema(description = "系统参数")
public class Parameter extends BaseEntity {

    @Schema(description = "编码")
    @Column(name = "code", unique = true, nullable = false)
    @NotBlank(message = "编码不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 3, max = 30, message = "编码为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    @Pattern(regexp = "[A-Za-z0-9\\-]{3,30}", message = "编码只能是3-30个(英文/数字)字符", groups = {Validator.Insert.class, Validator.Update.class})
    private String code;

    @Schema(description = "名称")
    @Column(name = "name")
    @Length(min = 1, max = 30, message = "编码为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    private String name;

    @Schema(description = "值")
    @Column(name = "value", nullable = false)
    @NotBlank(message = "值不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 255, message = "值为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    private String value;

    @Schema(description = "备注")
    @Column(name = "remark")
    private String remark;
}
