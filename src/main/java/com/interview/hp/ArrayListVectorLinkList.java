package com.interview.hp;

import java.util.Vector;

/**
 * @author bo bo
 * @date 2019/7/5 16:54
 * @desc
 */
public class ArrayListVectorLinkList {



    public static void main(String[] args) {
        People people = new People();
        people.setAge(11);
        people.setName("vector");
        Vector <People>vector = new Vector();
        vector.add(people);
        

    }

static class People  {
      private String name;
      private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
}


