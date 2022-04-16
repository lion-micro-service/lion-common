package com.lion.common.expose.parameter.impl;

import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.expose.parameter.ParameterExposeService;
import com.lion.core.service.impl.BaseServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

/**
 * @author mr.liu
 * @Description: 参数接口暴露
 * @date 2020/9/15上午10:29
 */
@DubboService(interfaceClass = ParameterExposeService.class)
public class ParameterExposeServiceImpl extends BaseServiceImpl<Parameter> implements ParameterExposeService {

    @Autowired
    private ParameterDao parameterDao;

    @Override
    public void testSeataTransactional(String code, String value) {
        Optional<Parameter> optional = parameterDao.findFirstByCode(code);
        Parameter parameter = null;
        if (!optional.isPresent()){
            parameter = new Parameter();
            parameter.setCode(code);
            parameter.setValue(value);
        }else {
            parameter = optional.get();
            parameter.setValue(UUID.randomUUID().toString());
        }
        this.save(parameter);
    }
}
