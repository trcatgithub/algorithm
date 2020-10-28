package unclassify;

import java.time.LocalDateTime;

// 限制1秒内只有100个请求能够被处理
public class Solution33 {
    // 记录一秒的开始时间
    private long prevSecond = 0;
    // 限流个数
    private int limit = 100;

    // 限流方法
    public boolean limit() {
        // 获取当前时间
        long now = System.currentTimeMillis();
        // 当前时间距离第一次调用未超过1秒
        if(now - prevSecond < 1000) {
            // limit大于0时准入
            if(limit > 0) {
                limit--;
                return true;
            }else {
                return false;
            }
        }else { // 当前时间距离第一次调用超过1秒时，重置计时器与计数器
            limit = 99;
            prevSecond = now;
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Solution33 s33 = new Solution33();
        // 一秒内调用200次，只有100次能够准入
        for(int i=0; i<200; i++) {
            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+s33.limit());
        }
        Thread.sleep(1000);
        // 一秒后，新调用的100次也能够准入
        for(int i=0; i<100; i++) {
            System.out.println(LocalDateTime.now()+"    i: "+i+"   "+s33.limit());
        }
    }
}
