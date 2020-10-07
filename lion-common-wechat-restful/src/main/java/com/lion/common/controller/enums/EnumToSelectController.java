package com.lion.common.controller.enums;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mr.liu
 * @Description: 枚举转selelct下拉框
 * @date 2020/9/15上午11:04
 */
@RestController
@RequestMapping("/common/enum/wechat")
@Validated
public class EnumToSelectController extends AbstractEnumToSelectController {
}
