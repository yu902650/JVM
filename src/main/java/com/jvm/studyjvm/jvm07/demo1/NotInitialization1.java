package com.jvm.studyjvm.jvm07.demo1;

/**
 * @author bo bo
 * @date 2019/7/13 9:58
 * @desc
 */
public class NotInitialization1 {

    public static void main(String[] args) {

        SuperClass[] sca = new SuperClass[10];
        System.out.println(sca);
    }

}
