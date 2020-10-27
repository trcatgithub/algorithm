package unclassify;

// 函数f() 返回1或者0的概率分别为p与(1-p)
// 利用f()封装一个函数F()使生成的1与0的概率为50%
public class Solution31 {
    // 70%概率生成1， 30%概率生成0
    private int f() {
        return Math.random() > 0.3 ? 1 : 0;
    }

    // 50%概率生成1与0
    public int F() {
        // 每次循环，计算两次f()
        // 当且仅当两次f()一次为0，另一次为1时才返回
        while(true) {
            int res1 = f();
            int res2 = f();
            if(res1 == 0 && res2 == 1) {
                return 0;
            }
            if(res1 == 1 && res2 == 0) {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        int oneCount = 0;
        int zeroCount = 1;
        Solution31 obj = new Solution31();
        for(int i=0; i<10000; i++) {
            if(obj.f() == 1) {
                oneCount++;
            }else {
                zeroCount++;
            }
        }
        System.out.println("zero: "+zeroCount+"   one: "+oneCount);
        oneCount = 0;
        zeroCount = 0;
        for(int i=0; i<1000; i++) {
            if(obj.F() == 1) {
                oneCount++;
            }else {
                zeroCount++;
            }
        }
        System.out.println("zero: "+zeroCount+"   one: "+oneCount);
    }
}
