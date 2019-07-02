package com.jldata;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Lijinliang
 * @date 2019/6/28 10:51
 */
public class Riqi {
    public static void main(String[] args) {
        Calendar c  = Calendar.getInstance();
        c.set(2019,6,27,16,0,0);
        System.out.println(c.getTime().getTime());
        c.set(2019,6,27,18,0,0);
        System.out.println(c.getTime().getTime());
        System.out.println(new Date().getTime() );
    }
}
