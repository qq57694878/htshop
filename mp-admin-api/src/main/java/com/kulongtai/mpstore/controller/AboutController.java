package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.About;
import com.kulongtai.mpstore.entity.Notice;
import com.kulongtai.mpstore.service.IAboutService;
import com.kulongtai.mpstore.service.INoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 关于我们表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/api/about")
public class AboutController {
    @Autowired
    private IAboutService iAboutService;
    @GetMapping("/getAbout")
    @ApiOperation(value="查询关于我们")
    public R<About> getAbout() {
        List<About> list = iAboutService.list();
        About about =null;
        if(list!=null&&list.size()>0){
            about = list.get(0);
        }
        return new R(about);
    }
    @PostMapping("/saveAbout")
    @ApiOperation(value="保存关于我们")
    public R<Boolean> saveAbout(@RequestBody About about) {
        iAboutService.remove(Wrappers.update());
        iAboutService.save(about);
        return new R(true);
    }
}
