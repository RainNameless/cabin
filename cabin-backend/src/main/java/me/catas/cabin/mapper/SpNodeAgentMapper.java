package me.catas.cabin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.catas.cabin.model.SpNodeAgent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 节点监控Mapper 接口
 *
 * @author Calisto
 * @since 2021-04-24
 */
@Mapper
public interface SpNodeAgentMapper extends BaseMapper<SpNodeAgent> {

    /**
     * 查询所有节点最后一笔监控数据
     **/
    List<SpNodeAgent> selectAgentDataLast();

    /**
     * 清理agent数据
     **/
    void deleteAgent();
}
