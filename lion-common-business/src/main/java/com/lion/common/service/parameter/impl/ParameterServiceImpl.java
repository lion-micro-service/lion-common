package com.lion.common.service.parameter.impl;

import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.mapper.ParameterMapper;
import com.lion.common.service.parameter.ParameterService;
import com.lion.common.vo.ParameterDetailTreeVo;
import com.lion.common.vo.ParameterTreeVo;
import com.lion.common.vo.ParameterVo;
import com.lion.core.*;
import com.lion.core.persistence.JpqlParameter;
import com.lion.core.service.impl.BaseServiceImpl;
import com.lion.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author mr.liu
 * @Description: Parameter Service
 * @date 2020/9/14下午5:12
 */
@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterDao parameterDao;

    @Override
    public Boolean checkCodeExist(String code, Long id) {
        Optional<Parameter> optional = parameterDao.findFirstByCode(code);
        if (!optional.isPresent()) {
            return false;
        }
        if (Objects.nonNull(id) && optional.get().getId().equals(id)) {
            return false;
        }
        return true;
    }

    @Override
    public void save(ParameterVo parameterVo) {
        Parameter parameter = ParameterMapper.INSTANCE.parameterVoToEntity(parameterVo);
        checkParameterExist(parameter);
        parameterDao.save(parameter);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Parameter> optional = parameterDao.findById(id);
        if (optional.isEmpty()) {
            new BusinessException("删除的数据不存在");
        }
        Parameter parameter = optional.get();
        List<Parameter> childList = parameterDao.findAllByParentId(parameter.getId());
        if (!ObjectUtils.isEmpty(childList) && childList.size() > 0) {
            childList.forEach(child -> {
                parameterDao.delete(child);
            });
        }
        parameterDao.delete(parameter);
    }

    @Override
    public void update(ParameterVo parameterVo) {
        Parameter parameter = ParameterMapper.INSTANCE.parameterVoToEntity(parameterVo);
        checkParameterExist(parameter);
        parameterDao.update(parameter);
    }

    @Override
    public IResultData findById(Long id) {
        Optional<Parameter> optional = parameterDao.findById(id);
        if (!optional.isPresent()) {
            return ResultData.instance().failed("查询结果为空");
        }
        ParameterVo parameterVo = ParameterMapper.INSTANCE.parameterEntityToVo(optional.get());
        return ResultData.instance().setData(parameterVo);
    }

    @Override
    public IPageResultData<ParameterVo> list(LionPage lionPage) {
        JpqlParameter jpqlParameter = new JpqlParameter();
        // 默认根据排序字段升序展示
        jpqlParameter.setSortParameter("sort", Sort.Direction.ASC);
        lionPage.setJpqlParameter(jpqlParameter);
        Page<Parameter> navigator = parameterDao.findNavigator(lionPage);
        List<Parameter> parameterList = navigator.getContent();
        ArrayList<ParameterVo> parameterVos = new ArrayList();
        for (Parameter parameter : parameterList) {
            parameterVos.add(ParameterMapper.INSTANCE.parameterEntityToVo(parameter));
        }
        return new PageResultData<>(parameterVos, lionPage, navigator.getTotalElements());
    }

    /**
     * 检查编码是否存在
     *
     * @param entity
     */
    private void checkParameterExist(Parameter entity) {
        if (checkCodeExist(entity.getCode(), entity.getId())) {
            new BusinessException("该编码已存在");
        }
    }
}
