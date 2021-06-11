package Lab3.First;

public class NewtonMethod {
        public double f1(double x) {
            return Math.acos(x);
        }

        public double f2(double x1, double x2) {
            return ((f1(x1) - f1(x2)) / (x1 - x2));
        }

        public double f3(double x1, double x2, double x3) {
            return ((f2(x1, x2) - f2(x2, x3)) / (x1 - x3));
        }

        public double f4(double x1, double x2, double x3, double x4) {
            return ((f3(x1, x2, x3) - f3(x2, x3, x4)) / (x1 - x4));
        }

        double[] X_i = {-0.4, 0, 0.2, 0.5};
        double X = 0.1;

        public void solve() {
            double[] f1 = new double[4];
            double[] f2 = new double[3];
            double[] f3 = new double[2];
            double f4 = 0;
            for (int i = 0; i < 4; i++) {
                f1[i] = f1(X_i[i]);
            }
            for (int i = 0; i < 3; i++) {
                f2[i] = f2(X_i[i], X_i[i + 1]);
            }
            for (int i = 0; i < 2; i++) {
                f3[i] = f3(X_i[i], X_i[i + 1], X_i[i + 2]);
            }
            f4 = f4(X_i[0], X_i[1], X_i[2], X_i[3]);
            double P = f1[0] + f2[0] * (X - X_i[0]) + f3[0] *(X - X_i[0])* (X - X_i[1]) + f4 * (X - X_i[0])*(X - X_i[1]) * (X - X_i[2]);

            System.out.println("P = "+P);
            System.out.println("f(x) = "+f1(X));
            System.out.println("Погрешность: " + Math.abs(P - f1(X)));
        }

        public static void main(String[]args){
            new NewtonMethod().solve();
        }


}
