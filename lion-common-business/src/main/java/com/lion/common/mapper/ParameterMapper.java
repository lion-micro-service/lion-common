package com.lion.common.mapper;

import com.lion.common.entity.parameter.Parameter;
import com.lion.common.vo.ParameterListVo;
import com.lion.common.vo.ParameterTreeVo;
import com.lion.common.vo.ParameterVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Mr.Liu
 * @classname ParameterMppaer
 * @description
 * @date 2022/04/16 上午11:34
 */
@Mapper
public interface ParameterMapper {
    ParameterMapper INSTANCE = Mappers.getMapper(ParameterMapper.class);

    public List<ParameterListVo> parameterToParameterListVo(List<Parameter> parameterList);

    public List<ParameterTreeVo> listToTreeList(List<Parameter> list);

    public Parameter parameterVoToEntity(ParameterVo parameterVo);

    public ParameterVo parameterEntityToVo(Parameter parameter);
}
