package com.lion.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.common.entity.parameter.Parameter;
import com.lion.core.persistence.Validator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author yuhuan
 * @Date 2022/6/8
 */
@Data
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"isDelete", "createDateTime", "updateDateTime", "createUserId", "updateUserId", "tenantId"}
)
@Schema(name = "数据字典模型")
public class ParameterVo extends Parameter {
    @Override
    @NotBlank(message = "编码不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 32, message = "编码为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    public String getCode() {
        return super.getCode();
    }

    @Override
    @NotBlank(message = "名称不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 32, message = "名称为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    public String getName() {
        return super.getName();
    }
}
