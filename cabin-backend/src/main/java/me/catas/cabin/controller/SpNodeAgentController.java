package me.catas.cabin.controller;

import lombok.extern.slf4j.Slf4j;
import me.catas.cabin.response.Result;
import me.catas.cabin.service.SpNodeAgentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 节点监控业务用户接口
 */
@Slf4j
@RestController
@RequestMapping("/agent")
public class SpNodeAgentController {

    @Resource
    private SpNodeAgentService spNodeAgentService;

    @GetMapping("/list")
    public Result<?> list() {
        return Result.success(spNodeAgentService.getAgentDataLast());
    }

}
