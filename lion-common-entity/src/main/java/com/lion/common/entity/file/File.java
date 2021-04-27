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
@Table(name = "t_file")
@DynamicUpdate
@DynamicInsert
@Data
@ApiModel(description = "文件")
public class File extends BaseEntity {

    @ApiModelProperty(value = "原始文件名")
    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @ApiModelProperty(value = "文件名")
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @ApiModelProperty(value = "文件大小")
    @Column(name = "size", nullable = false)
    private Long size;

    @ApiModelProperty(value = "文件下载路径")
    @Column(name = "url", nullable = false)
    private String url;
}
