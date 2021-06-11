package Lab2.Second;

public class NewtonsMethod {

    public static void main(String[] args) {
        new NewtonsMethod().solve();
    }

    public float det(float[][] a) {
        return ((a[0][0] * a[1][1]) - (a[0][1] * a[1][0]));
    }

    public float f1(float x1, float x2) {
        return x1 * x1 + x2 * x2 - 16;
    }

    public float f2(float x1, float x2) {
        return (float) (x1 - Math.exp(x2) + 4);
    }

    public float f1_derx1(float x1, float x2) {
        return 2 * x1;
    }

    public float f1_derx2(float x1, float x2) {
        return 2 * x2;
    }

    public float f2_derx1(float x1, float x2) {
        return 1;
    }

    public float f2_derx2(float x1, float x2) {
        return (float) -Math.exp(x2);
    }

    public float norm(float a, float b) {
        return Math.abs(b - a);
    }

    public void solve() {
        float x1 = 0.25f;
        float x2 = 1.75f;

        float eps = 0.0001f;
        int count = 0;
        float norm_max = 1;

        System.out.println(("\nSolving by Newton method:\n"));
        System.out.println(("-----------------------------"));
        System.out.println(("|     x1      |      x2     |"));
        System.out.println(("-----------------------------"));

        while (norm_max > eps) {
            float[][] J = new float[][]{
                    {f1_derx1(x1, x2), f1_derx2(x1, x2)},
                    {f2_derx1(x1, x2), f2_derx2(x1, x2)}
            };

            float[][] A1 = new float[][]{
                    {f1(x1, x2), f1_derx2(x1, x2)},
                    {f2(x1, x2), f2_derx2(x1, x2)}
            };
            float[][] A2 = new float[][]{
                    {f1_derx1(x1, x2), f1(x1, x2)},
                    {f2_derx1(x1, x2), f2(x1, x2)}
            };
            float x1_k = x1 - (det(A1)) / (det(J));
            float x2_k = x2 - (det(A2)) / (det(J));
            float tmp1 = norm(x1, x1_k);
            float tmp2 = norm(x2, x2_k);
            if (tmp1 > tmp2) norm_max = tmp1;
            else norm_max = tmp2;
            count += 1;
            x1 = x1_k;
            x2 = x2_k;
            System.out.println(" " + x1_k + "     " + x2_k);
        }
        System.out.println("Iterations: " + count);
    }
}