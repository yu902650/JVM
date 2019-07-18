package com.jvm.studyjvm.jvm08;

/**
 * @author bo bo
 * @date 2019/7/15 11:54
 * @desc  局部变量表Slot复用对垃圾收集的影响
 */
public class TestGCVerbose {

    public static void main(String[] args) {

        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }

}
