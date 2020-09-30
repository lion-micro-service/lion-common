package com.lion.common.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.core.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author mr.liu
 * @Description: 保存所有微服务的枚举值，用于前端生成select下拉框
 * @date 2020/9/16下午3:52
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_common_enum_util",uniqueConstraints = {@UniqueConstraint(columnNames = {"class"})})
@DynamicUpdate
@DynamicInsert
@Data
@JsonIgnoreProperties(ignoreUnknown = true,value = {"createDateTime","updateDateTime","createUserId","updateUserId"})
public class EnumUtil extends BaseEntity {
    private static final long serialVersionUID = -2124525197256620973L;

    @Column(name = "class",nullable = false,unique = true)
    private String classs;

    @Column(name = "value",nullable = false)
    private String value;

}
