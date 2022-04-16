package com.lion.common.entity.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.common.bean.definition.file.FileDefinition;
import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @description: 文件
 * @author: mr.liu
 * @create: 2020-10-08 14:16
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_file")
@DynamicInsert
@Data
public class File extends FileDefinition {
    private static final long serialVersionUID = -2124525197256620973L;
}
