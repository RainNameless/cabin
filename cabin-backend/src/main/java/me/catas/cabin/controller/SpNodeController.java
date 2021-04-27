package me.catas.cabin.controller;

import lombok.extern.slf4j.Slf4j;
import me.catas.cabin.model.PageQuery;
import me.catas.cabin.model.SpNode;
import me.catas.cabin.response.Result;
import me.catas.cabin.service.SpNodeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节点操作接口
 */
@Slf4j
@RestController
@RequestMapping("/node")
public class SpNodeController {

    @Resource
    private SpNodeService spNodeService;

    @PostMapping("/list")
    public Result<?> findNodes(@RequestBody PageQuery<SpNode> queryParam) {
        return Result.success(spNodeService.findList(queryParam));
    }

    @PostMapping("/save")
    public Result<?> saveNode(@Validated @RequestBody SpNode spNode) {
        spNodeService.saveNode(spNode);
        return Result.success();
    }

    @PutMapping("/edit")
    public Result<?> editNode(@Validated @RequestBody SpNode spNode) {
        spNodeService.updateNode(spNode);
        return Result.success();
    }

    @DeleteMapping("/del")
    public Result<?> removeNode(@RequestParam List<Long> nodeIds) {
        spNodeService.removeNodeByIds(nodeIds);
        return Result.success();
    }

}
