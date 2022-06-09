package com.lion.common.service.parameter;

import com.lion.common.entity.parameter.Parameter;
import com.lion.common.vo.ParameterDetailTreeVo;
import com.lion.common.vo.ParameterTreeVo;
import com.lion.common.vo.ParameterVo;
import com.lion.core.IPageResultData;
import com.lion.core.IResultData;
import com.lion.core.LionPage;
import com.lion.core.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author mr.liu
 * @Description: parameter service
 * @date 2020/9/14下午5:12
 */
public interface ParameterService {

    /**
     * 检查编码是否存在
     *
     * @param code 编码
     * @param id
     * @return
     */
    public Boolean checkCodeExist(String code, Long id);

    /**
     * 添加数据字典
     */
    public void save(ParameterVo parameterVo);

    /**
     * 根据id删除字典
     *
     * @param id
     */
    public void deleteById(Long id);

    /**
     * 删除数据字典
     *
     * @param parameterVo
     */
    public void update(ParameterVo parameterVo);

    /**
     * 根据ID查询字典
     *
     * @param id 字典ID
     */
    public IResultData findById(Long id);

    /**
     * 字典列表展示
     *
     * @param lionPage 分页参数
     */
    public IPageResultData<ParameterVo> list(LionPage lionPage);

//    /**
//     * 根据编码查询参数设置
//     *
//     * @param code
//     * @return
//     */
//    public Optional<Parameter> findParameter(String code);

//    /**
//     * 根据id列表删除字典
//     *
//     * @param ids
//     */
//    public void deleteByIds(List<Long> ids);

//    public List<ParameterTreeVo> listTree(Long id);

//    public ParameterDetailTreeVo detailTree(String code);
}
