package com.lion.common.entity.parameter;

import com.lion.common.bean.definition.parameter.ParameterDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author mr.liu
 * @Description: 系统参数
 * @date 2020/9/14下午4:31
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_parameter",indexes = {@Index(columnList = "create_date_time") })
@Data
public class Parameter extends ParameterDefinition {

}