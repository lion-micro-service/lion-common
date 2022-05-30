package com.lion.common.mapper;

import com.lion.common.bean.definition.file.FileDefinition;
import com.lion.common.entity.file.File;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zvoon
 * @date 2022/5/30 10:42
 * @description
 */
@Mapper
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    public FileDefinition entityToDefinition(File file);

    public List<FileDefinition> convertToDefinitionList(List<File> list);

}
