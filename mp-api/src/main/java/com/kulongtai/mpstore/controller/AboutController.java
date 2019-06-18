package com.kulongtai.mpstore.controller;


import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.entity.About;
import com.kulongtai.mpstore.service.IAboutService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 关于我们表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/about")
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
}
