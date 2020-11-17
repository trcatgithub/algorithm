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
        ThreadDemo ta = new Solution2().new ThreadDemo(sa, sb, "A");
        ThreadDemo tb = new Solution2().new ThreadDemo(sb, sc, "B");
        ThreadDemo tc = new Solution2().new ThreadDemo(sc, sa, "C");
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

    class ThreadDemo implements Runnable {

        private volatile boolean flag = true;
        private Semaphore current;
        private Semaphore next;
        private String val;

        public ThreadDemo(Semaphore current, Semaphore next, String val) {
            this.current = current;
            this.next = next;
            this.val = val;
        }

        public void shutdown() {
            this.flag = false;
        }

        @Override
        public void run() {
            while(this.flag) {
                try {
                    this.current.acquire(1);
                    System.out.print(this.val);
                    this.next.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
