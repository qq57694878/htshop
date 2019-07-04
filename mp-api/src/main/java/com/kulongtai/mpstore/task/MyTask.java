package com.kulongtai.mpstore.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.entity.Card;
import com.kulongtai.mpstore.service.ICardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lijinliang
 * @date 2019/6/24 9:17
 */
@Component
@Configurable
@EnableScheduling
@Slf4j
public class MyTask {
    @Autowired
    private ICardService iCardService;

    /**
     * 处理过期的卡片致状态为无效
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void processExpireCard() {
        UpdateWrapper<Card> updateWrapper = Wrappers.update();
        updateWrapper.set("valid_flag", "0");
        updateWrapper.set("update_time", new Date());
        updateWrapper.eq("valid_flag", "1");
        updateWrapper.lt("valide_time", new Date());
        iCardService.update(updateWrapper);
    }
}
