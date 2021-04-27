package me.catas.cabin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.catas.cabin.mapper.SpNodeMapper;
import me.catas.cabin.model.PageQuery;
import me.catas.cabin.model.SpNode;
import me.catas.cabin.service.SpNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节点接口实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpNodeServiceImpl extends ServiceImpl<SpNodeMapper, SpNode> implements SpNodeService {

    @Resource
    private SpNodeMapper spNodeMapper;

    @Override
    public PageInfo<SpNode> findList(PageQuery<SpNode> queryParam) {
        PageHelper.startPage(queryParam.getCurrent(), queryParam.getPageSize());
        List<SpNode> spNodes = spNodeMapper.selectNodeList(queryParam.getParam());
        return new PageInfo<>(spNodes);
    }

    @Override
    public void saveNode(SpNode spNode) {
        spNodeMapper.insert(spNode);
    }

    @Override
    public void updateNode(SpNode spNode) {
        spNodeMapper.updateNode(spNode);
    }

    @Override
    public void removeNodeByIds(List<Long> nodeIds) {
        spNodeMapper.deleteBatchIds(nodeIds);
    }
}
