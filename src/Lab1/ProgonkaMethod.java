package Lab1;

public class ProgonkaMethod {
    public static void main(String[] args) {
        int A[][] = {{8, -4, 0, 0, 0},
                {-2, 12, -7, 0, 0},
                {0, 2, -9, 1, 0},
                {0, 0, -8, 17, -4},
                {0, 0, 0, -7, 13}};

        int B[] = {32, 15, -10, 133, -76};
        int n = A.length;
//System.out.println(n);
        double p[] = new double[n];
        double q[] = new double[n];
        int x[] = new int[n];
        p[0] = -A[0][1] / A[0][0];
        q[0] = B[0] / A[0][0];
        for (int i = 1; i < n - 1; i++) {
            p[i] = -A[i][i + 1] / (A[i][i] + A[i][i - 1] * p[i - 1]);
            q[i] = (B[i] - A[i][i - 1] * q[i - 1]) / (A[i][i] + A[i][i - 1] * p[i - 1]);
        }
        p[n - 1] = 0;
        q[n - 1] = (B[n - 1] - A[n - 1][n - 2] * q[n - 2]) / (A[n - 1][n - 1] + A[n - 1][n - 2] * p[n - 2]);
        x[n - 1] = (int) q[n - 1];
        for (int i = n - 2; i > -1; i--)
            x[i] = (int) (p[i] * x[i + 1] + q[i]);
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i + 1) + " " + x[i]);
        }

    }
}
