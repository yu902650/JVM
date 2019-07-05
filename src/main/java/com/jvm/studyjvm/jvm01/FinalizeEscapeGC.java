package com.jvm.studyjvm.jvm01;

/**
 * @author bo bo
 * @date 2019/7/5 7:54
 * @desc
 *
 * finalize method executed!
 * yes, i am still alive
 * no , i am dead :(
 *
 * 第一次调用finalize()方法成功逃过线程的回收.但第二次被回收,因为所有方法只会执行一次.finalize()方法.
 *
 * 尽量不使用这个finalize方法去写代码.
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {

        System.out.println("yes, i am still alive");

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {

        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead :(");
        }

        //下面这段代码与上面完全相同,但是自救失败了.
        SAVE_HOOK = null;
        System.gc();

        //因为finalize方法优先级很低,所以暂停0.5s , 以等待它
        Thread.sleep(500);

        if (SAVE_HOOK !=null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no , i am dead :(");
        }
    }
}
