package com.lion.common.expose.parameter.impl;

import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.expose.parameter.ParameterExposeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.UUID;

/**
 * @author mr.liu
 * @Description: 参数接口暴露
 * @date 2020/9/15上午10:29
 */
@DubboService(interfaceClass = ParameterExposeService.class)
public class ParameterExposeServiceImpl extends com.lion.core.service.impl.BaseServiceImpl<Parameter> implements ParameterExposeService {

    @Autowired
    private ParameterDao parameterDao;

    @Override
    public void testSeataTransactional(String code, String value) {
        Parameter parameter = parameterDao.findFirstByCode(code);
        if (Objects.isNull(parameter)){
            parameter = new Parameter();
            parameter.setCode(code);
            parameter.setValue(value);
        }else {
            parameter.setValue(UUID.randomUUID().toString());
        }
        this.save(parameter);
    }
}
