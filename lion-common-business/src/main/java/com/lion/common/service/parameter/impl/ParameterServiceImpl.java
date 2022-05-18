package com.lion.common.service.parameter.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.service.parameter.ParameterService;
import com.lion.core.service.impl.BaseServiceImpl;
import com.lion.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public java.util.Optional<Parameter> findParameter(String code) {
        return parameterDao.findFirstByCode(code);
    }

    @Override
    public Boolean checkCodeExist(String code, Long id) {
        Optional<Parameter> optional = parameterDao.findFirstByCode(code);
        if (!optional.isPresent()){
            return false;
        }
        if (Objects.nonNull(id) && optional.get().getId().equals(id)){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkCodeExist(String code) {
        return checkCodeExist(code, null);
    }

    @Override
    public List<Parameter> findByParentCode(String code) {
        return parameterDao.findAllByParentCode(code);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(i->{
            com.lion.core.Optional<Parameter> optional = findById(i);
            if (optional.isPresent()){
                Parameter parameter = optional.get();
                List<Parameter> childList = findByParentCode(parameter.getCode());
                if (Objects.nonNull(childList) && childList.size() > 0){
                    childList.forEach(child ->{
                        deleteByParentCode(child.getCode());
                        delete(child);
                    });
                }
            }
            deleteById(i);
        });
    }

    public void deleteByParentCode(String code) {
            List<Parameter> parentCodeList = findByParentCode(code);
            if (Objects.nonNull(parentCodeList) && parentCodeList.size()>0){
                parentCodeList.forEach(parameter -> {
                    deleteByParentCode(parameter.getCode());
                    delete(parameter);
                });
            }
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
