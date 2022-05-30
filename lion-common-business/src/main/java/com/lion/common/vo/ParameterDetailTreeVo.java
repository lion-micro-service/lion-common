package com.lion.common.vo;

import com.lion.common.entity.parameter.Parameter;
import lombok.Data;

import java.util.List;

/**
 * @author zvoon
 * @date 2022/5/23 10:06
 * @description
 */
@Data
public class ParameterDetailTreeVo extends Parameter{

    private List<Parameter> parameters;

}
