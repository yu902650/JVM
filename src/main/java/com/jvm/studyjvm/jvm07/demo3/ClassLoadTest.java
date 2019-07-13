package com.jvm.studyjvm.jvm07.demo3;
import	java.util.Map;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author bo bo
 * @date 2019/7/13 13:16
 * @desc  比较同一个类,是否相同.
 *
 * 类与类加载器:
 * 类加载器虽然只实现类的加载动作,但他在java程序中起到的作用却远不止类加载阶段.
 * 比较两个类是否相等,只有在这两个类是由同一个类加载器加载的前提下才有意义.
 * 否则,即使这两个类源于同一Class文件,被同一虚拟机加载,只要加载他们的类加载器不同,那么这两个类必定不相等.
 *
 */
public class ClassLoadTest {

    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.jvm.studyjvm.jvm07.demo3.ClassLoadTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.jvm.studyjvm.jvm07.demo3.ClassLoadTest);
    }

}
