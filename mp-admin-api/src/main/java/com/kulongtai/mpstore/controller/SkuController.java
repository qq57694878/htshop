package com.kulongtai.mpstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kulongtai.mpstore.common.R;
import com.kulongtai.mpstore.dto.SkuListDto;
import com.kulongtai.mpstore.entity.Sku;
import com.kulongtai.mpstore.service.ISkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-02
 */

@Api(value = "商品管理")
@RestController
@RequestMapping("/api/sku")
public class SkuController {
    @Autowired
    private ISkuService iSkuService;

    @GetMapping("/getSkuList")
    @ApiOperation(value="查询商品列表", notes="需传入分页参数")
    public R<IPage> getSkuList(SkuListDto skuListDto) {
        QueryWrapper<Sku> queryWrapper = Wrappers.<Sku>query();
        queryWrapper.like(StringUtils.isNotBlank(skuListDto.getSkuName()),"sku_name",skuListDto.getSkuName()).eq(StringUtils.isNotBlank(skuListDto.getSkuStatus()),"sku_status",skuListDto.getSkuStatus());
        queryWrapper.eq("del_flag","0");
        queryWrapper.orderByDesc("create_time");
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
    @DeleteMapping("/deleteSku")
    @ApiOperation(value="删除商品")
    @ApiImplicitParam(paramType="query", name = "id", value = "商品id", required = true, dataType = "Integer")
    public R deleteSku(@RequestParam(required = true) Integer id) {
        Sku sku =new Sku();
        sku.setSkuId(id);
        sku.setDelFlag("1");
        boolean f= iSkuService.updateById(sku);
        return new R(f);
    }
    @PostMapping("/insertSku")
    @ApiOperation(value="插入商品")
    public R<Boolean> insertSku(@RequestBody Sku sku) {
        sku.setDelFlag("0");
        sku.setCreateTime(new Date());
        boolean f = iSkuService.save(sku);
        return new R(f);
    }
    @PutMapping("/updateSku")
    @ApiOperation(value="更新商品")
    public R<Boolean> updateSku(@RequestBody Sku sku) {
        boolean f = iSkuService.updateById(sku);
        return new R(f);
    }
}
