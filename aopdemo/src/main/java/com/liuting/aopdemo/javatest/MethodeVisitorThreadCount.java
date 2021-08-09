package com.liuting.aopdemo.javatest;

import java.util.concurrent.Semaphore;

/**
 * 作者:admin on 2020/4/15 09:46
 * 邮箱:474211389@qq.com
 * 项目名：liuting_project
 * 包名：com.liuting.aopdemo.javatest
 * TODO:如何控制某个方法允许并发访问线程的个数?
 */
public class MethodeVisitorThreadCount {
    private static Semaphore semaphore=new Semaphore(6);

    public static void main(String[] args)  {
        MethodeVisitorThreadCount methodeVisitorThreadCount=new MethodeVisitorThreadCount();
        for (int i = 0 ; i < 50;i++)
        {
            Thread myThread = new Thread()
            {
                @Override
                public void run() {
                    super.run();
                    try {
                        methodeVisitorThreadCount.test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            myThread.setName("threat index:" + i);
            myThread.start();

        }
    }

    public void test() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + "--in");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "--out" );
        semaphore.release();

    }
}
