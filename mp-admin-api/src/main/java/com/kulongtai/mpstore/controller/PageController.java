package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.Notice;
import com.kulongtai.mpstore.entity.Page;
import com.kulongtai.mpstore.service.INoticeService;
import com.kulongtai.mpstore.service.IPageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/api/page")
public class PageController {
    @Autowired
    private IPageService iPageService;
    @GetMapping("/getHomePage")
    @ApiOperation(value="查询首页模板")
    public R<Page> getHomePage() {
        Page page = iPageService.getOne(Wrappers.<Page>query().eq("page_type","10"));
        return new R(page);
    }
    @PostMapping("/saveHomePage")
    @ApiOperation(value="保存首页模板")
    public R<Boolean> saveHomePage(@RequestBody Page page) {
        iPageService.saveOrUpdate(page);
        return new R(true);
    }
}
