package com.interview.hp;

/**
 * @author bo bo
 * @date 2019/7/5 13:41
 * @desc String StringBuffer的区别
 * String       长度不可变长.
 * StringBuffer 长度可变长.
 */
public class StringAndStringBuffer {

    public static void main(String[] args) {
        String string = new String("长度不可变长");
        System.out.println("String: " + string);

        StringBuffer buffer = new StringBuffer("长度可变长");
        buffer.append("我是后面加的");
        System.out.println("StringBuffer: " + buffer);

    }
}
