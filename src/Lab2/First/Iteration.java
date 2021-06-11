package Lab2.First;

public class Iteration {
    public static float f(float x) {
        return x * x * x + x * x - 2 * x - 1;
    }

    public static float derF(float x) {
        return 3 * x * x - 2 * x - 2;
    }

//    public static float phi(float x) {
//        return (x * x * x + x * x - 1) / 2;
//    }
//    public  static  float derPhi(float x){
//        return x+(3*x*x/2);
//    }

    public static float phi(float x, float max) {
        return  x - Math.signum(derF(x)) / max * f(x);
    }

    public static float derPhi(float x, float max) {
        return -Math.signum(derF(x)) / max * derF(x);
    }

public void solve(){
    float a = 1.1f;
    float b = 2.1f;
    float xPrev = (b - a) / 2f + a;
    float eps = 0.0001f;
    int count = 0;
    float max = Math.abs(derF(a));
    float tmp=0;
    float x = a + 0.01f;
        while (x <= b) {
        tmp = Math.abs(derF(x));
        if (tmp > max)
            max = tmp;
        x += 0.01f;
    }
    System.out.println(max);
    float q = Math.abs(derPhi( a,max));
    x = a + 0.01f;
            while (x <= b) {
        if (Math.abs(derPhi(x,max)) > q)
            q = Math.abs(derPhi( x,max));
        x += 0.01f;
    }
    System.out.println(x);
    x = phi(xPrev,max);
        while (q / (1 - q) * Math.abs(x - xPrev) > eps) {
            xPrev = x;
            x = phi(x,max);
            count++;
            System.out.println(x);
        }
    System.out.println(count);
    }

    public static void main(String[] args) {
        new Iteration().solve();
    }
}
