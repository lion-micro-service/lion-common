package com.lion.common.bean.definition.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lion.core.persistence.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;

/**
 * @description: 文件
 * @author: mr.liu
 * @create: 2020-10-08 14:16
 **/
@Data
@Schema(description = "文件")
@MappedSuperclass
public class FileDefinition extends BaseEntity {

    private static final long serialVersionUID = -2124525197256620973L;

    @Schema(description = "原始文件名")
    protected String originalFileName;

    @Schema(description = "文件名")
    protected String fileName;

    @Schema(description = "文件大小")
    protected Long size;

    @Schema(description = "文件下载路径")
    protected String url;
}
