package com.jvm.studyjvm.jvm07.demo3.DestroyParentalAppointmentMechanism;

import java.io.*;

/**
 * @author bo bo
 * @date 2019/7/13 14:39
 * @desc
 */
public class TestClassLoaderN extends ClassLoader {

    private String name;

    public TestClassLoaderN(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        ClassLoader system = getSystemClassLoader();
        try {
            clazz = system.loadClass(name);
        } catch (Exception e) {
            // ignore
        }
        if (clazz != null)
            return clazz;
        clazz = findClass(name);
        return clazz;
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
        TestClassLoaderN loader = new TestClassLoaderN(
                TestClassLoaderN.class.getClassLoader(), "TestLoaderN");
        Class clazz;
        try {
            clazz = loader.loadClass("test.classloader.Test");
            Object object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
