package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
//
//存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
//
//氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
//
//这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
//
//你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
//
//换句话说:
//
//如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
//如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
//书写满足这些限制条件的氢、氧线程同步代码。
//
// 
//
//示例 1:
//
//输入: "HOH"
//输出: "HHO"
//解释: "HOH" 和 "OHH" 依然都是有效解。
//示例 2:
//
//输入: "OOHHHH"
//输出: "HHOHHO"
//解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
// 
//
//提示：
//
//输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
//输入字符串中的 “H” 总数将会是 2n 。
//输入字符串中的 “O” 总数将会是 n 。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/building-h2o
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution3 {

//    // 基于信号量
//    // 24ms/40MB
//    static class H2O{
//        // 初始情况: 可以打印2个H原子 不能打印O原子
//        private Semaphore h = new Semaphore(2);
//        private Semaphore o = new Semaphore(0);
//
//        public H2O() {
//
//        }
//
//        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//            h.acquire(1);
//            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//            releaseHydrogen.run();
//            o.release(1);
//        }
//
//        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//            o.acquire(2);
//            // releaseOxygen.run() outputs "O". Do not change or remove this line.
//            releaseOxygen.run();
//            h.release(2);
//        }
//    }

    // 基于wait notifyAll
    // 20ms/39.8MB
    static class H2O{
        // 初始情况: 可以打印2个H原子 不能打印O原子
        private volatile int hFlag = 2;
        private volatile int oFlag = 0;

        public H2O() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (this) {
                while(hFlag == 0) {
                    this.wait();
                }
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                hFlag--;
                oFlag++;
                this.notifyAll();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (this) {
                while(oFlag < 2) {
                    this.wait();
                }
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                oFlag = 0;
                hFlag = 2;
                this.notifyAll();
            }
        }
    }

    static class HydrogenThread implements Runnable {
        @Override
        public void run() {
            System.out.print("H");
        }
    }

    static class OxygenThread implements Runnable {
        @Override
        public void run() {
            System.out.print("O");
        }
    }

    static class Helper{

        public static void start(String input) throws InterruptedException {
            HydrogenThread hThread = new HydrogenThread();
            OxygenThread oThread = new OxygenThread();
            H2O h2o = new H2O();
            for(char c : input.toCharArray()) {
                if(c == 'H') {
                    new Thread(() -> {
                        try {
                            h2o.hydrogen(hThread);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }else {
                    new Thread(() -> {
                        try {
                            h2o.oxygen(oThread);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        Helper.start("HHHHOO");
        Helper.start("OOOOOOOOOOOOOOOOOHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
    }

}
