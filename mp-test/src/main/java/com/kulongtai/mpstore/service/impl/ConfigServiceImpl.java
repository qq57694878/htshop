package com.kulongtai.mpstore.service.impl;

import com.kulongtai.mpstore.entity.Config;
import com.kulongtai.mpstore.mapper.ConfigMapper;
import com.kulongtai.mpstore.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author lijinliang
 * @since 2019-06-19
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
