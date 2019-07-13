package com.jvm.studyjvm.jvm07.demo1;

/**
 * @author bo bo
 * @date 2019/7/13 10:17
 * @desc
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO_WORLD="hello world";

}
