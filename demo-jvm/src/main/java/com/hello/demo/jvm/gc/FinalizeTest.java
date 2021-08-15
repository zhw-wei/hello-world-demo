package com.hello.demo.jvm.gc;

/**
 * 测试垃圾回收 finalize
 * 可达性分析算法，根对象
 * @author zhw
 * @date 2021/8/15 10:24 下午
 */
public class FinalizeTest {

    private static FinalizeTest obj = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("调用当前类重写的finalize()方法");
        //当前待回收的对象在finalize()方法中与引用链上的一个对象obj建立了联系
        obj = this;
    }

    public static void main(String[] args) {
        try{

            obj = new FinalizeTest();

            //对象第一次成功拯救自己
            obj = null;

            System.gc();//调用垃圾回收器
            System.out.println("第一次 GC");

            //因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);

            if(obj == null){
                System.out.println("obj is dead");
            }else{
                System.out.println("obj is still alive");
            }

            //================================================
            System.out.println("第2次 GC");
            //下面这段代码与上面的完全相同，但是这次却自救失败了
            //因为该对象的finalize方法已经被调用过了
            obj = null;

            System.gc();//调用垃圾回收器
            System.out.println("第一次 GC");

            //因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);

            if(obj == null){
                System.out.println("obj is dead");
            }else{
                System.out.println("obj is still alive");
            }

        }catch (Exception ex){

        }
    }
}
