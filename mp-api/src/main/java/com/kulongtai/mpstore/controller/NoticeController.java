package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.Notice;
import com.kulongtai.mpstore.service.INoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/notice")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;
    @GetMapping("/getNotice")
    @ApiOperation(value="查询通知通告")
    public R<Notice> getNotice() {
        QueryWrapper<Notice>queryWrapper = Wrappers.<Notice>query();
        queryWrapper.eq("del_flag","1");
        queryWrapper.lt("start_time",new Date());
        queryWrapper.gt("end_time",new Date());
        queryWrapper.last("limit 1");
        Notice notice = iNoticeService.getOne(queryWrapper);
        return new R(notice);
    }
}
