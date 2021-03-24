package com.lion.common.expose.parameter;

import com.lion.common.entity.parameter.Parameter;

/**
 * @author mr.liu
 * @Description: 参数接口暴露
 * @date 2020/9/15上午10:29
 */
public interface ParameterExposeService extends com.lion.core.service.BaseService<Parameter> {

    /**
     *
     * @param code
     * @param value
     */
    public void testSeataTransactional(String code,String value);
}
