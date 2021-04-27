package me.catas.cabin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import me.catas.cabin.model.PageQuery;
import me.catas.cabin.model.SpNode;

import java.util.List;

/**
 * 节点接口
 *
 * @author Calisto
 * @since 2021-03-16
 */
public interface SpNodeService extends IService<SpNode> {

    /**
     * 分页查询节点列表
     **/
    PageInfo<SpNode> findList(PageQuery<SpNode> queryParam);

    /**
     * 保存节点
     **/
    void saveNode(SpNode spNode);

    /**
     * 更新节点
     **/
    void updateNode(SpNode spNode);

    /**
     * 批量删除节点
     **/
    void removeNodeByIds(List<Long> nodeIds);

}
