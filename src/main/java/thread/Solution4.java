package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

//编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
//
//如果这个数字可以被 3 整除，输出 "fizz"。
//如果这个数字可以被 5 整除，输出 "buzz"。
//如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
//例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
//
//假设有这么一个类：
//
//class FizzBuzz {
//    public FizzBuzz(int n) { ... }               // constructor
//    public void fizz(printFizz) { ... }          // only output "fizz"
//    public void buzz(printBuzz) { ... }          // only output "buzz"
//    public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
//    public void number(printNumber) { ... }      // only output the numbers
//}
//请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
//
//线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
//线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
//线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
//线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class Solution4 {

    // 信号量，终止条件放在各个方法的循环体
    // 10ms/38.4MB
    class FizzBuzz {
        private int n;
        private Semaphore fizzSemaphore = new Semaphore(0);
        private Semaphore buzzSemaphore = new Semaphore(0);
        private Semaphore fizzBuzzSemaphore = new Semaphore(0);
        private Semaphore numberSemaphore = new Semaphore(1);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for(int i=3; i<=n; i+=3) {
                if(i%5 != 0) {
                    fizzSemaphore.acquire();
                    printFizz.run();
                    numberSemaphore.release();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for(int i=5; i<=n; i+=5) {
                if(i%3 != 0) {
                    buzzSemaphore.acquire();
                    printBuzz.run();
                    numberSemaphore.release();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for(int i=15; i<=n; i+=15) {
                fizzBuzzSemaphore.acquire();
                printFizzBuzz.run();
                numberSemaphore.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for(int i=1; i<=n; i++) {
                numberSemaphore.acquire();
                if(i%3 == 0 && i%5 == 0) {
                    fizzBuzzSemaphore.release();
                }else if(i%3 == 0) {
                    fizzSemaphore.release();
                }else if(i%5 == 0) {
                    buzzSemaphore.release();
                }else {
                    printNumber.accept(i);
                    numberSemaphore.release();
                }
            }
        }
    }

//    // 基于信号量
//    // 8ms/38.8MB
//    class FizzBuzz {
//        private int n;
//        private int current = 1;
//        private Semaphore fizzSemaphore = new Semaphore(0);
//        private Semaphore buzzSemaphore = new Semaphore(0);
//        private Semaphore fizzBuzzSemaphore = new Semaphore(0);
//        private Semaphore numberSemaphore = new Semaphore(1);
//        private volatile boolean flag = false;
//
//        public FizzBuzz(int n) {
//            this.n = n;
//        }
//
//        // printFizz.run() outputs "fizz".
//        public void fizz(Runnable printFizz) throws InterruptedException {
//            while(current <= n) {
//                fizzSemaphore.acquire();
//                if(flag) {
//                    return;
//                }
//                if(current%5 != 0) {
//                    printFizz.run();
//                    tryStop();
//                    current++;
//                    numberSemaphore.release();
//                }
//            }
//        }
//
//        // printBuzz.run() outputs "buzz".
//        public void buzz(Runnable printBuzz) throws InterruptedException {
//            while(current <= n) {
//                buzzSemaphore.acquire();
//                if(flag) {
//                    return;
//                }
//                if(current%3 != 0) {
//                    printBuzz.run();
//                    tryStop();
//                    current++;
//                    numberSemaphore.release();
//                }
//            }
//        }
//
//        // printFizzBuzz.run() outputs "fizzbuzz".
//        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//            while(current <= n) {
//                fizzBuzzSemaphore.acquire();
//                if(flag) {
//                    return;
//                }
//                if(current%15 == 0) {
//                    printFizzBuzz.run();
//                    tryStop();
//                    current++;
//                    numberSemaphore.release();
//                }
//            }
//        }
//
//        // printNumber.accept(x) outputs "x", where x is an integer.
//        public void number(IntConsumer printNumber) throws InterruptedException {
//            while(current <= n) {
//                numberSemaphore.acquire();
//                if(flag) {
//                    return;
//                }
//                if(current%3 != 0 && current%5 != 0) {
//                    printNumber.accept(current);
//                    tryStop();
//                    current++;
//                    if(current%3 == 0 && current%5 == 0) {
//                        fizzBuzzSemaphore.release();
//                    }else if(current%3 == 0) {
//                        fizzSemaphore.release();
//                    }else if(current%5 == 0) {
//                        buzzSemaphore.release();
//                    }else {
//                        numberSemaphore.release();
//                    }
//                }else if(current%3 == 0) {
//                    fizzSemaphore.release();
//                }else {
//                    buzzSemaphore.release();
//                }
//            }
//        }
//
//        private void tryStop() {
//            if(current == n) {
//                flag = true;
//                fizzSemaphore.release();
//                buzzSemaphore.release();
//                fizzBuzzSemaphore.release();
//                numberSemaphore.release();
//            }
//        }
//    }

    static class PrintDemo implements Runnable {

        private String val;

        public PrintDemo(String val) {
            this.val = val;
        }
        @Override
        public void run() {
            System.out.print(val+", ");
        }
    }

    static class Helper {
        public static void start(int n) {
            ExecutorService pool = Executors.newFixedThreadPool(4);
            FizzBuzz fizzBuzz = new Solution4().new FizzBuzz(n);
            pool.execute(()->{
                try {
                    fizzBuzz.buzz(new PrintDemo("buzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pool.execute(()->{
                try {
                    fizzBuzz.fizz(new PrintDemo("fizz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pool.execute(()->{
                try {
                    fizzBuzz.fizzbuzz(new PrintDemo("fizzbuzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pool.execute(()->{
                try {
                    fizzBuzz.number((num)->{
                        System.out.print(num+", ");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            pool.shutdown();
        }
    }

    public static void main(String[] args) {
//        Helper.start(15);
        Helper.start(15);
    }
}
