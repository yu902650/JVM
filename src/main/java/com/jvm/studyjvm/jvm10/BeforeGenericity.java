package com.jvm.studyjvm.jvm10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bo bo
 * @date 2019/7/17 8:48
 * @desc
 *
 * 泛型擦除前
 */
public class BeforeGenericity {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<String, String>();

        map.put("hello","你好");
        map.put("how are you?","吃了没?");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));

    }

}
