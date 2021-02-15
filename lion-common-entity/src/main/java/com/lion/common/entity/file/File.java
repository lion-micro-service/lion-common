package com.lion.common.entity.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "t_common_file",indexes = {@Index(columnList = "create_date_time") })
@DynamicUpdate
@DynamicInsert
@Data
@ApiModel(description = "文件")
public class File extends BaseEntity {

    @ApiModelProperty(value = "新选择的用户")
    @Column(name = "original_file_name", nullable = false, columnDefinition = " varchar(255) comment '原始文件名' ")
    private String originalFileName;

    @ApiModelProperty(value = "文件大小")
    @Column(name = "size", nullable = false, columnDefinition = " BIGINT(18) comment '文件大小（bit）' ")
    private Long size;

    @ApiModelProperty(value = "文件下载路径")
    @Column(name = "url", nullable = false, columnDefinition = " varchar(255) comment '文件下载路径' ")
    private String url;
}