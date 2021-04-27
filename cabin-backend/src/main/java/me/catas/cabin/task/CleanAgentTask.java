package me.catas.cabin.task;

import lombok.extern.slf4j.Slf4j;
import me.catas.cabin.mapper.SpNodeAgentMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CleanAgentTask {

    @Resource
    private SpNodeAgentMapper spNodeAgentMapper;

    /**
     * 每晚00点清除监控数据(仅保留一天)
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    private void truncate() {
        spNodeAgentMapper.deleteAgent();
        log.info("清理Agent数据成功");
    }
}
