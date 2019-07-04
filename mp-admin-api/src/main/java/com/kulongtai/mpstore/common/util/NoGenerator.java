package com.kulongtai.mpstore.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lijinliang on 2019/6/2 0002.
 *
 */
public class NoGenerator {

    private final static long BASE_NUM = 10000000000L;
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyMMddHHmmss");

    /**
     * 生成订单号 16位，前12位为日期按（yyMMddHHmmss）格式化 +4位随机数
     * @return
     */
    public static String orderNo() {
        return SDF.format(new Date())+String.format("%04d",new Double(Math.random()*10000).intValue());
    }

    /**
     * 生成卡券兑换码：总12位整数 前2位（66：次数卡88E卡）业务卡类型+10位随机数
     * @return
     */
    public static long cardNo() {
        long baseNum =  66 * BASE_NUM;
        return baseNum + new Double(Math.random() * BASE_NUM).longValue();
    }


}
