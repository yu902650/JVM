package com.jvm.studyjvm.jvm01;

/**
 * @author bo bo
 * @date 2019/7/4 14:05
 * @desc 引用计数算法
 */
public class ReferenceCountingGC {

    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存.以便能在GC日志中看清楚是否被回收过.
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //GC 看 objA objB 是否被回收
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC c = new ReferenceCountingGC();
    }
}
