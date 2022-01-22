package com.lion.common.service.parameter;

import com.lion.common.entity.parameter.Parameter;
import com.lion.core.service.BaseService;

import java.util.Optional;

/**
 * @author mr.liu
 * @Description: parameter service
 * @date 2020/9/14下午5:12
 */
public interface ParameterService extends BaseService<Parameter> {

    /**
     * 根据编码查询参数设置
     * @param code
     * @return
     */
    public Optional<Parameter> findParameter(String code);

    /**
     * 检查编码是否存在
     * @param code
     * @param id
     * @return
     */
    public Boolean checkCodeExist(String code, Long id);

    /**
     * 检查编码是否存在
     * @param code
     * @return
     */
    public Boolean checkCodeExist(String code);
}
