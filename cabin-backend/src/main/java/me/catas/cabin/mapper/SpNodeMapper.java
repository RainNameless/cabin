package me.catas.cabin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.catas.cabin.model.SpNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 节点Mapper 接口
 *
 * @author Calisto
 * @since 2021-03-16
 */
@Mapper
public interface SpNodeMapper extends BaseMapper<SpNode> {

    /**
     * 查询节点集合
     **/
    List<SpNode> selectNodeList(SpNode spNode);


    /**
     * 更新节点信息
     **/
    void updateNode(SpNode spNode);
}
