package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//map中只有一个kv，k是"key1"，value是0。
//写一段代码，开10个线程，每个线程将map中k是key1的value取出来后，执行++，然后put回去。
//要求无论执行多少次，一定可以将key1的value后变成10。
public class Solution1 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ThreadDemo t = new ThreadDemo();
        for(int i=0; i<10; i++) {
            pool.execute(t);
        }
        System.out.println(t.getValue());
        pool.shutdown();
    }
}

class ThreadDemo implements Runnable {

    Map<String, Integer> memo = new HashMap<>();

    public ThreadDemo() {
        memo.put("key1", 0);
    }

    @Override
    public synchronized void run() {
        memo.put("key1", memo.get("key1")+1);
    }

    public int getValue() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return memo.get("key1");
    }
}
