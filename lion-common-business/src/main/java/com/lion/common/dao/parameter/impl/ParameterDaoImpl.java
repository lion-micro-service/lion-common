package com.lion.common.dao.parameter.impl;

import com.lion.common.dao.parameter.ParameterDaoEx;
import com.lion.common.entity.parameter.Parameter;
import com.lion.core.persistence.curd.BaseDao;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author mr.liu
 * @Description:参数复杂sql操作扩展
 * @date 2020/9/14下午5:16
 */
public class ParameterDaoImpl implements ParameterDaoEx {

    @Autowired
    private BaseDao<Parameter> baseDao;

}
