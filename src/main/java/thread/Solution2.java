package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// 3个线程循环打印ABC
// 利用信号量
public class Solution2 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore sa = new Semaphore(1);
        Semaphore sb = new Semaphore(1);
        Semaphore sc = new Semaphore(1);
        sb.acquire(1);
        sc.acquire(1);
        ThreadDemoA ta = new Solution2().new ThreadDemoA(sa, sb);
        ThreadDemoB tb = new Solution2().new ThreadDemoB(sb, sc);
        ThreadDemoC tc = new Solution2().new ThreadDemoC(sc, sa);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(ta);
        pool.execute(tb);
        pool.execute(tc);
        Thread.sleep(10);
        ta.shutdown();
        tb.shutdown();
        tc.shutdown();
        pool.shutdown();
    }

    class ThreadDemoA implements Runnable {

        private volatile boolean flag = true;
        private Semaphore sa;
        private Semaphore sb;

        public ThreadDemoA(Semaphore sa, Semaphore sb) {
            this.sa = sa;
            this.sb = sb;
        }

        public void shutdown() {
            this.flag = false;
        }

        @Override
        public void run() {
            while(flag) {
                try {
                    sa.acquire(1);
                    System.out.print("A");
                    sb.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadDemoB implements Runnable {

        private volatile boolean flag = true;
        private Semaphore sb;
        private Semaphore sc;

        public ThreadDemoB(Semaphore sb, Semaphore sc) {
            this.sb = sb;
            this.sc = sc;
        }

        public void shutdown() {
            this.flag = false;
        }

        @Override
        public void run() {
            while(flag) {
                try {
                    sb.acquire(1);
                    System.out.print("B");
                    sc.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadDemoC implements Runnable {

        private volatile boolean flag = true;
        private Semaphore sc;
        private Semaphore sa;

        public ThreadDemoC(Semaphore sc, Semaphore sa) {
            this.sc = sc;
            this.sa = sa;
        }

        public void shutdown() {
            this.flag = false;
        }

        @Override
        public void run() {
            while(flag) {
                try {
                    sc.acquire(1);
                    System.out.println("C");
                    sa.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
