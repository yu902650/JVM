package com.jvm.studyjvm.jvm10;

import java.util.Arrays;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/7/17 9:11
 * @desc 自动装箱, 拆箱 与 遍历循环
 */
public class AutoUnpackIncasing {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        //如果jdk 1.7 . 还有另外一颗语法糖
        //能让上面这句代码进一步 简写成 List<Integer> list = [1,2,3,4];
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
