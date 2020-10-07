package com.lion.common.controller.parameter;

import com.lion.core.controller.BaseController;
import com.lion.core.controller.impl.BaseControllerImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mr.liu
 * @Description: 参数controller
 * @date 2020/9/15上午11:06
 */
@RestController
@RequestMapping("/common/parameter/app")
@Validated
public class ParameterController extends BaseControllerImpl implements BaseController {
}
