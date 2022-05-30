package com.lion.common.service.parameter.impl;

import com.lion.common.dao.parameter.ParameterDao;
import com.lion.common.entity.parameter.Parameter;
import com.lion.common.mapper.ParameterMapper;
import com.lion.common.service.parameter.ParameterService;
import com.lion.common.vo.ParameterDetailTreeVo;
import com.lion.common.vo.ParameterTreeVo;
import com.lion.core.service.impl.BaseServiceImpl;
import com.lion.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteByIds(List<Long> ids) {
        ids.forEach(i->{
            com.lion.core.Optional<Parameter> optional = findById(i);
            if (optional.isPresent()){
                Parameter parameter = optional.get();
                List<Parameter> childList = parameterDao.findAllByParentId(parameter.getId());
                if (Objects.nonNull(childList) && childList.size() > 0){
                    childList.forEach(child ->{
                        deleteByParentId(child.getId());
                        delete(child);
                    });
                }
            }
            deleteById(i);
        });
    }

    @Override
    public List<ParameterTreeVo> listTree(Long id) {
        List<Parameter> allByParentId = parameterDao.findAllByParentId(id);
        List<ParameterTreeVo> parameterTreeVos = ParameterMapper.INSTANCE.listToTreeList(allByParentId);
        if (Objects.nonNull(parameterTreeVos) && parameterTreeVos.size() > 0){
            parameterTreeVos.forEach(parameter -> {
                List<ParameterTreeVo> treeVoListById = listTree(parameter.getId());
                parameter.setChildList(treeVoListById);
            });
        }
        return parameterTreeVos;
    }

    @Override
    public ParameterDetailTreeVo detailTree(String code) {
        Optional<Parameter> codeOptional = parameterDao.findFirstByCode(code);
        if (codeOptional.isEmpty()){
            BusinessException.throwException("没有找到当前code："+code);
        }
        Parameter parameter = codeOptional.get();
        ParameterDetailTreeVo vo = new ParameterDetailTreeVo();
        BeanUtils.copyProperties(parameter,vo);
        List<Parameter> allByParentId = parameterDao.findAllByParentId(vo.getId());
        if (Objects.nonNull(allByParentId) && allByParentId.size()>0){
             vo.setParameters(allByParentId);
        }
        return vo;
    }


    public void deleteByParentId(Long id) {
            List<Parameter> parentCodeList = parameterDao.findAllByParentId(id);
            if (Objects.nonNull(parentCodeList) && parentCodeList.size()>0){
                parentCodeList.forEach(parameter -> {
                    deleteByParentId(parameter.getId());
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
