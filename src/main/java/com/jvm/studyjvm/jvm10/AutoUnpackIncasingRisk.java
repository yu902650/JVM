package com.jvm.studyjvm.jvm10;

import java.net.Inet4Address;

/**
 * @author bo bo
 * @date 2019/7/17 9:27
 * @desc 自动装箱的陷阱
 */
public class AutoUnpackIncasingRisk {

    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}

// 包装类" == " 运算在不遇到算数运算的情况下不会自动拆箱,以及他们的equals方法不处理数据转型的关系,实际代码编写中尽量避免自动装箱和拆箱

/**
 true
 false
 true
 true
 true
 false
 */
