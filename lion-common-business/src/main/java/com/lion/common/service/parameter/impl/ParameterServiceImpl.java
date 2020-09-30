package com.lion.common.service.parameter.impl;

import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.service.parameter.ParameterService;
import com.lion.core.service.impl.BaseServiceImpl;
import com.lion.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author mr.liu
 * @Description: Parameter Service
 * @date 2020/9/14下午5:12
 */
@Service
public class ParameterServiceImpl extends BaseServiceImpl<Parameter> implements ParameterService {

    @Autowired
    private ParameterDao parameterDao;

    @Override
    public Parameter findParameter(String code) {
        return parameterDao.findFirstByCode(code);
    }

    @Override
    public Boolean checkCodeExist(String code, Long id) {
        Parameter parameter = parameterDao.findFirstByCode(code);
        if (Objects.isNull(parameter)){
            return false;
        }
        if (Objects.nonNull(id) && parameter.getId().equals(id)){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkCodeExist(String code) {
        return checkCodeExist(code, null);
    }

    @Override
    public void update(Parameter entity) {
        checkParameterExist(entity);
        super.update(entity);
    }

    @Override
    public <S extends Parameter> S save(S entity) {
        checkParameterExist(entity);
        return super.save(entity);
    }

    /**
     * 检查编码是否存在
     * @param entity
     */
    private void checkParameterExist(Parameter entity){
        if (checkCodeExist(entity.getCode(), entity.getId())){
            new BusinessException("该编码已存在");
        }
    }
}
