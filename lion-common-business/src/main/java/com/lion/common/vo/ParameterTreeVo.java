package com.lion.common.vo;

import com.lion.common.entity.parameter.Parameter;
import lombok.Data;

import java.util.List;

/**
 * @author zvoon
 * @date 2022/5/19 13:39
 * @description
 */
@Data
public class ParameterTreeVo extends Parameter {

    private List<ParameterTreeVo> childList;

}
