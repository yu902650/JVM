package com.jvm.studyjvm.jvm07.demo3.DestroyParentalAppointmentMechanism;

/**
 * @author bo bo
 * @date 2019/7/13 14:26
 * @desc
 */
public class Test {
    public Test(){
        System.out.println(this.getClass().getClassLoader().toString());
    }
}
