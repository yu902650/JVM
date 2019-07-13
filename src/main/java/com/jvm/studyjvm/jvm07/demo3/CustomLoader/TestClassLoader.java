package com.jvm.studyjvm.jvm07.demo3.CustomLoader;

import java.io.*;

/**
 * @author bo bo
 * @date 2019/7/13 14:17
 * @desc
 *
 * 定义一个TestClassLoader类继承ClassLoader，
 * 重写findClass方法，此方法要做的事情是读取Test.class字节流并传入父类的defineClass方法即可。
 * 然后就可以通过自定义累加载器TestClassLoader对Test.class进行加载,完成加载后会输出“TestLoader”。
 *
 */
public class TestClassLoader extends ClassLoader {
    private String name;

    public TestClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> findClass(String name) {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(new File("d:/Test.class"));
            int c = 0;
            while (-1 != (c = is.read())) {
                baos.write(c);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) {
        TestClassLoader loader = new TestClassLoader(
                TestClassLoader.class.getClassLoader(), "TestLoader");
        Class clazz;
        try {
            clazz = loader.loadClass("test.classloader.Test");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
