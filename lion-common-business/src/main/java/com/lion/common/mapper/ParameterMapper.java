package com.lion.common.mapper;

import com.lion.common.dto.CuParameterDto;
import com.lion.common.entity.parameter.Parameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Mr.Liu
 * @classname ParameterMppaer
 * @description
 * @date 2022/04/16 上午11:34
 */
@Mapper
public interface ParameterMapper {
    ParameterMapper INSTANCE  = Mappers.getMapper(ParameterMapper.class);

    public Parameter CuParameterDtoToParameter(CuParameterDto cuParameterDto);
}
