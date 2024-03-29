package com.lion.common.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "t_enum_util")

@DynamicInsert
@Data
@Schema(description = "保存所有微服务的枚举值，用于前端生成select下拉框")
public class EnumUtil extends BaseEntity {
    private static final long serialVersionUID = -2124525197256620973L;

    @Schema(description = "枚举key值")
    @Column(name = "class",nullable = false,unique = true)
    private String classs;

    @Schema(description = "枚举value值")
    @Column(name = "value",nullable = false,length = 2000)
    private String value;

}
