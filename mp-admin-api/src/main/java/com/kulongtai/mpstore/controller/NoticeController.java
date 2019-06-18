package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.Notice;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.service.INoticeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;
    @GetMapping("/getNotice")
    @ApiOperation(value="查询通知通告")
    public R<Notice> getNotice() {
        List<Notice> list = iNoticeService.list();
        Notice notice =null;
        if(list!=null&&list.size()>0){
            notice = list.get(0);
        }
        return new R(notice);
    }
    @PostMapping("/saveNotice")
    @ApiOperation(value="保存通知通告")
    public R<Boolean> saveNotice(@RequestBody Notice notice) {
        iNoticeService.remove(Wrappers.update());
        notice.setDelFlag("0");
        iNoticeService.save(notice);
        return new R(true);
    }

}
