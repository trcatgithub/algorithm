package unclassify;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 限制1秒内只有100个请求能够被处理
public class Solution33 {
//    // 单线程版本
//    // 记录一秒的开始时间
//    private long prevSecond = 0;
//    // 限流个数
//    private int limit = 100;
//
//    // 限流方法
//    public boolean limit() {
//        // 获取当前时间
//        long now = System.currentTimeMillis();
//        // 当前时间距离第一次调用未超过1秒
//        if(now - prevSecond < 1000) {
//            // limit大于0时准入
//            if(limit > 0) {
//                limit--;
//                return true;
//            }else {
//                return false;
//            }
//        }else { // 当前时间距离第一次调用超过1秒时，重置计时器与计数器
//            limit = 99;
//            prevSecond = now;
//            return true;
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        Solution33 s33 = new Solution33();
//        // 一秒内调用200次，只有100次能够准入
//        for(int i=0; i<200; i++) {
//            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+s33.limit());
//        }
//        Thread.sleep(1000);
//        // 一秒后，新调用的100次也能够准入
//        for(int i=0; i<100; i++) {
//            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+s33.limit());
//        }
//    }

//    // 时间由单独线程负责更新
//    // 业务线程只负责请求 以及 更新剩余准入个数
//    public static void main(String[] args) throws InterruptedException {
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
//        ThreadDemo t = new ThreadDemo();
//        pool.scheduleWithFixedDelay(t, 0, 1000, TimeUnit.MILLISECONDS);
//        // 一秒内调用200次，只有100次能够准入
//        for(int i=0; i<200; i++) {
//            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+t.limit());
//        }
//        Thread.sleep(600);
//        // 0.6秒后，新调用的100次不能准入
//        for(int i=0; i<100; i++) {
//            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+t.limit());
//        }
//        Thread.sleep(400);
//        // 0.4秒后，新调用的100次能够准入
//        for(int i=0; i<100; i++) {
//            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+t.limit());
//        }
//        pool.shutdown();
//    }
//
//    static class PrintDemo implements Runnable {
//        @Override
//        public void run() {
//            while(true) {
//                System.out.println(ThreadDemo.time+" : "+System.currentTimeMillis());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    static class ThreadDemo implements Runnable {
//
//        static long time;
//
//        volatile int limit = 100;
//
//        public boolean limit() {
//            long now = System.currentTimeMillis();
//            if(now - time < 1000) {
//                if(limit > 0) {
//                    limit--;
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public ThreadDemo() {
//            time = System.currentTimeMillis();
//        }
//
//        @Override
//        public void run() {
//            time = System.currentTimeMillis();
//            limit = 100;
//        }
//    }
}
