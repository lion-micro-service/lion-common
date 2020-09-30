package com.lion.common.expose.parameter.impl;

import com.lion.common.entity.parameter.Parameter;
import com.lion.common.expose.parameter.ParameterExposeService;
import com.lion.core.service.impl.BaseExposeServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author mr.liu
 * @Description: 参数接口暴露
 * @date 2020/9/15上午10:29
 */
@DubboService(interfaceClass = ParameterExposeService.class)
public class ParameterExposeServiceImpl extends BaseExposeServiceImpl<Parameter> implements ParameterExposeService {
}
