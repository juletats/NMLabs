package Lab2.First;

public class Newton {
    public static float f(float x) {
        return x * x * x + x * x - 2 * x - 1;
    }
    public static float derF(float x) {
        return 3 * x * x - 2 * x - 2;
    }

    public static float  solve() {
        float a = 1.1f;
        float b = 1.5f;
        float xPrev = 0;
        float eps =  0.0001f;
        int count= 0;
        float x =1.247f ;
        while (Math.abs(x - xPrev) > eps) {
            xPrev = x;
            x -= f(x) / derF(x);
            count++;
            System.out.println(x);
        }
        System.out.println("iterations " + count + "\n" +x);
        return x;
    }

    public static void main(String[] args) {
       float x = solve();
      //  System.out.println( f(x));
    }
}
