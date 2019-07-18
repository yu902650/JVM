package com.jvm.studyjvm.jvm10;

import java.util.List;

/**
 * @author bo bo
 * @date 2019/7/17 8:54
 * @desc 当泛型遇到重载.
 * 代码无法被编译的.
 * 因为编译后的 参数List<String>,List<Integer>都被擦除了.变成了原生的List<E>.
 */
public class GenericcTypes1 {

    public static void method(List<String> list) {

        System.out.println("invoke method (List<String> list)");

    }

    //此段代码要放出来
//    public static void method(List<Integer> list){
//
//        System.out.println("invoke method (List<Integer> list)");
//
//    }

}
