package com.jvm.studyjvm.jvm04;

/**
 * @author bo bo
 * @date 2019/7/9 13:53
 * @desc
 */
public class SynAddRunable implements Runnable {

    int a, b;

    public SynAddRunable(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunable(1, 2)).start();
            new Thread(new SynAddRunable(2, 1)).start();
        }
    }
}


/**
 * jconsole 中线程
 * 检测死锁
 * 锁持有
 * 互相持有对方的锁 block
 */
