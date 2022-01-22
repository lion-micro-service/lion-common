package com.lion.common.dao.parameter;

import com.lion.common.entity.parameter.Parameter;
import com.lion.core.persistence.curd.BaseDao;

import java.util.Optional;

/**
 * @author mr.liu
 * @Description: Parameter Dao
 * @date 2020/9/14下午5:15
 */
public interface ParameterDao extends BaseDao<Parameter>, ParameterDaoEx {

    /**
     * 根据编码查询参数设置
     * @param code
     * @return
     */
    public Optional<Parameter> findFirstByCode(String code);
}
