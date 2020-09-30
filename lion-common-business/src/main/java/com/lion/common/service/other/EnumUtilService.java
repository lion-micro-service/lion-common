package com.lion.common.service.other;

import com.lion.common.entity.other.EnumUtil;
import com.lion.core.service.BaseService;

/**
 * @author mr.liu
 * @Description:
 * @date 2020/9/16下午4:17
 */
public interface EnumUtilService extends BaseService<EnumUtil> {

    /**
     * 根据class查询枚举值
     * @param classs
     * @return
     */
    public EnumUtil find(String classs);
}
