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
     * @param bussType 业务分类（1次数卡2E卡4套餐）
     * @return
     */
    public static long cardNo(String bussType) {
        long result = 0l;
        long baseNum = 0l;
        if(bussType==null){
            bussType="1";
        }
        switch (bussType) {
            case "1":
                baseNum = 66 * BASE_NUM;
                break;
            case "2":
                baseNum = 88 * BASE_NUM;
                break;
            default:
                baseNum = 66 * BASE_NUM;
                break;
        }
        return baseNum + new Double(Math.random() * BASE_NUM).longValue();
    }

    public static void main(String[] args) {
        long l = cardNo("1");
        System.out.println(l);
        l = cardNo("2");
        System.out.println(l);
        l = cardNo("3");
        System.out.println(l);
        l = cardNo(null);
        System.out.println(l);
        for(int i=0;i<1000;i++){
            String s = orderNo();
            System.out.println(s);
        }

    }
}
