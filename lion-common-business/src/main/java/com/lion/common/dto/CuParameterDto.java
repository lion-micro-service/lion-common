package com.lion.common.dto;

import com.lion.common.bean.definition.parameter.ParameterDefinition;
import com.lion.core.persistence.Validator;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
/**
 * @author Mr.Liu
 * @classname CuParameterDto
 * @description
 * @date 2022/04/16 上午11:25
 */
@Data
public class CuParameterDto extends ParameterDefinition {

    @Override
    @NotBlank(message = "编码不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 32, message = "编码为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    @Pattern(regexp = "[A-Za-z0-9\\-]{1,32}", message = "编码只能是3-30个(英文/数字)字符", groups = {Validator.Insert.class, Validator.Update.class})
    public String getCode() {
        return super.getCode();
    }

    @Override
    @NotBlank(message = "名称不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 30, message = "名称为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    @Pattern(regexp = "[A-Za-z0-9\\-]{1,32}", message = "名称只能是3-30个(英文/数字)字符", groups = {Validator.Insert.class, Validator.Update.class})
    public String getName() {
        return super.getName();
    }

    @Override
    @NotBlank(message = "值不能为空", groups = {Validator.Insert.class, Validator.Update.class})
    @Length(min = 1, max = 255, message = "值为{min}-{max}个字符", groups = {Validator.Insert.class, Validator.Update.class})
    public String getValue() {
        return super.getValue();
    }
}
