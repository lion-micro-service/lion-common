package com.lion.common.entity.parameter;

import com.lion.common.bean.definition.parameter.ParameterDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "t_parameter",indexes = {@Index(columnList = "create_date_time") ,@Index(columnList = "tenant_id"),@Index(columnList = "name"),@Index(columnList = "code"),@Index(columnList = "parent_id"),@Index(columnList = "sort")})
@SQLDelete(sql = "update t_parameter set is_delete = 1 where id = ? and version = ?")
@Where(clause = " is_delete=0 ")
@Data
public class Parameter extends ParameterDefinition {

}
