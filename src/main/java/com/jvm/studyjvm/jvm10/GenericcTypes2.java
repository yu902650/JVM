package com.jvm.studyjvm.jvm10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/7/17 8:58
 * @desc 当泛型遇到重载2
 *
 */
public class GenericcTypes2 {

//    public static String method(List<String> list) {
//
//        System.out.println("invoke method (List<String> list)");
//
//        return "";
//    }

    public static int method(List<Integer> list) {

        System.out.println("invoke method (List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());

    }


}
