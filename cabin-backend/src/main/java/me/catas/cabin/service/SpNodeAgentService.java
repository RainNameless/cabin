package me.catas.cabin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.catas.cabin.model.SpNodeAgent;

import java.util.List;

/**
 * 节点监控服务类
 *
 * @author Calisto
 * @since 2021-04-24
 */
public interface SpNodeAgentService extends IService<SpNodeAgent> {

    /**
     * 查询各节点最后一笔最新监控数据
     **/
    List<SpNodeAgent> getAgentDataLast();
}
