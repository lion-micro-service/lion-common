package com.lion.common.service.other.impl;

import com.lion.common.dao.other.EnumUtilDao;
import com.lion.common.entity.other.EnumUtil;
import com.lion.common.service.other.EnumUtilService;
import com.lion.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author mr.liu
 * @Description:
 * @date 2020/9/16下午4:17
 */
@Service
public class EnumUtilServiceImpl extends BaseServiceImpl<EnumUtil> implements EnumUtilService {

    @Autowired
    private EnumUtilDao enumUtilDao;

    @Override
    public <S extends EnumUtil> S save(S entity) {
        this.enumUtilDao.deleteByClasss(entity.getClasss());
        return super.save(entity);
    }

    @Override
    public Optional<EnumUtil> find(String classs) {
        return enumUtilDao.findFirstByClasss(classs);
    }
}
