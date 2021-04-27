package me.catas.cabin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.catas.cabin.mapper.SpNodeAgentMapper;
import me.catas.cabin.model.SpNodeAgent;
import me.catas.cabin.service.SpNodeAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节点监控服务实现类
 *
 * @author Calisto
 * @since 2021-04-24
 */
@Service
public class SpNodeAgentServiceImpl extends ServiceImpl<SpNodeAgentMapper, SpNodeAgent> implements SpNodeAgentService {

    @Resource
    private SpNodeAgentMapper spNodeAgentMapper;

    @Override
    public List<SpNodeAgent> getAgentDataLast() {
        return spNodeAgentMapper.selectAgentDataLast();
    }
}
