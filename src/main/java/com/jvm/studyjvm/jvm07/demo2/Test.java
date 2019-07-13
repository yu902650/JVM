package com.jvm.studyjvm.jvm07.demo2;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author bo bo
 * @date 2019/7/13 11:43
 * @desc
 */
public class Test {

    static {
        i = 0;                      //给变量赋值可以正常编译通过
        System.out.println();      //这句编译器会提示非法向前引用
    }

    static int i = 1;

    static class Parent {
        public static int A = 2;

        static {
            A = 1;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

//    public static void main(String[] args) {
//        System.out.println(Sub.B);
//    }

    static class DeadLoopClass{
        static {
            if (true){
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
            }

        }
    }

}
