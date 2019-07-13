package com.jvm.studyjvm.jvm07.demo3.CustomLoader;

/**
 * @author bo bo
 * @date 2019/7/13 14:16
 * @desc  自定义加载器
 * 先定义一个待加载的类Test，它很简单，只是在构建函数中输出由哪个类加载器加载。
 */
public class Test {

    public Test(){
        System.out.println(this.getClass().getClassLoader().toString());
    }

}
