package com.jvm.studyjvm.jvm10;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/7/17 9:19
 * @desc 自动装箱, 拆箱 与 遍历循环
 */
public class AutoUnpackIncasing1 {

    public static void main(String[] args) {
        List list = Arrays.asList(new Integer[]{
                Integer.valueOf(1),
                Integer.valueOf(2),
                Integer.valueOf(3),
                Integer.valueOf(4)});

        int sum = 0;

        for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) {
            int i = ((Integer) localIterator.next()).intValue();
            sum += i;
        }

        System.out.println(sum);
    }

}
