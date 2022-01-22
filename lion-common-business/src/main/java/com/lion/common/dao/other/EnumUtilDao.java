package com.lion.common.dao.other;

import com.lion.common.entity.other.EnumUtil;
import com.lion.core.persistence.curd.BaseDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author mr.liu
 * @Description:
 * @date 2020/9/16下午4:32
 */
public interface EnumUtilDao extends BaseDao<EnumUtil> {

    /**
     * 根据class查询枚举
     * @param classs
     * @return
     */
    public Optional<EnumUtil> findFirstByClasss(String classs);

    /**
     * 根据class删除枚举
     * @param classs
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteByClasss(String classs);
}
