package Lab3.First;

import java.util.ArrayList;
import java.util.List;

//y=arccos(x) a) X_i= -0.4,-0.1,0.2,0.5; b)X_i=-0.4,0,0.2,0.5,X*=0.1
public class LagrangeMethod {
    public double function(double x){
        return Math.acos(x);
    }
    public void solve() {
        double[] X_i = {-0.4, 0, 0.2, 0.5};
        double X =0.1;
        List<Double> W = new ArrayList<>();
        List<Double> F = new ArrayList<>();
        List<Double> Res = new ArrayList<>();
        List<Double> Difference = new ArrayList<>();
        W.add((X_i[0]-X_i[1])*(X_i[0]-X_i[2])*(X_i[0]-X_i[3]));
        W.add((X_i[1]-X_i[0])*(X_i[1]-X_i[2])*(X_i[1]-X_i[3]));
        W.add((X_i[2]-X_i[0])*(X_i[2]-X_i[1])*(X_i[2]-X_i[3]));
        W.add((X_i[3]-X_i[0])*(X_i[3]-X_i[1])*(X_i[3]-X_i[2]));

        for (int i = 0; i <4 ; i++) {
            F.add(function(X_i[i]));
        }
        for (int i = 0; i < 4; i++) {
            Res.add(F.get(i)/W.get(i));
            Difference.add(X-X_i[i]);
        }
        double L = Res.get(0)*(X-X_i[1])*(X-X_i[2])*(X-X_i[3])+
                Res.get(1)*(X-X_i[0])*(X-X_i[2])*(X-X_i[3])+
                Res.get(2)*(X-X_i[0])*(X-X_i[1])*(X-X_i[3])+
                Res.get(3)*(X-X_i[0])*(X-X_i[1])*(X-X_i[2]);
//       System.out.println(Arrays.toString(X_i));
//        System.out.println(F);
//        System.out.println(W);
//        System.out.println(Res);
        System.out.println("L =" +L);
        System.out.println("f(x) = "+ function(X));
        System.out.println("Погрешность = " + Math.abs(function(X)-L));
    }

    public static void main(String[] args) {
        new LagrangeMethod().solve();
    }
}
