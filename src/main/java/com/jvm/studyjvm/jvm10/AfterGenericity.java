package com.jvm.studyjvm.jvm10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bo bo
 * @date 2019/7/17 8:50
 * @desc
 *
 * 泛型擦除后
 */
public class AfterGenericity {

    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("hello","你好");
        map.put("how are you?","吃了没?");
        System.out.println((String) map.get("hello"));
        System.out.println((String)map.get("how are you?"));
    }


}
