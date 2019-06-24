package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.service.ISkuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-13
 */
@RestController
@RequestMapping("/mpapi/sku")
public class SkuController {
    @Autowired
    private ISkuService iSkuService;

    @GetMapping("/getSkuList")
    @ApiOperation(value="查询商品列表", notes="需传入分页参数")
    public R<IPage> getSkuList(SkuListDto skuListDto) {
        QueryWrapper<Sku> queryWrapper = Wrappers.<Sku>query();
        queryWrapper.like(StringUtils.isNotBlank(skuListDto.getSkuName()),"sku_name",skuListDto.getSkuName()).eq(StringUtils.isNotBlank(skuListDto.getSkuStatus()),"sku_status",skuListDto.getSkuStatus());
        queryWrapper.eq("del_flag","0");
        //1上架 2下架
        queryWrapper.eq("sku_status","1");
        queryWrapper.orderByAsc("sort");
        IPage<Sku> skuList = iSkuService.page(new Page<>(skuListDto.getCurrent(),skuListDto.getSize()),queryWrapper);
        return new R(skuList);
    }
    @GetMapping("/getSku")
    @ApiOperation(value="查询商品详情")
    @ApiImplicitParam(paramType="query", name = "id", value = "商品id", required = true, dataType = "Integer")
    public R<Sku> getSku(@RequestParam(required = true) Integer id) {
        Sku sku = iSkuService.getById(id);
        return new R(sku);
    }
}
