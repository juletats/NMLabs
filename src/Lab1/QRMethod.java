package Lab1;

import Matrix.Matrix;

public class QRMethod {
    private static final float[][] a = new float[][]{
            {-5, -8, 4},
            {4, 2, 6},
            {-2, 5, -6}
    };
    private static final float epsilon = 0.001f;

    public static void solve() {

        int amount = 0;
        Matrix A = new Matrix(a);
        Matrix Ab = new Matrix(a);
        Matrix Q = new Matrix(3, 1);
        Matrix V = new Matrix(3, 1, 0);
        Matrix H = new Matrix(3, 3, 0);
        Matrix E = new Matrix(3, 1f);
        float tmp;
        A.printMatrix();

        while (A.triangleNorm() > epsilon ) {
            Q = new Matrix(3, 1f);
            for (int i = 0; i < A.matrix.length-1; i++) {
                V = new Matrix(3, 1, 0f);
                V.matrix[i][0] = A.matrix[i][i];
                tmp = 0f;
                for (int j = i; j < A.matrix.length; j++) {
                    tmp += A.matrix[j][i] * A.matrix[j][i];
                }
                V.matrix[i][0] += Math.signum(A.matrix[i][i]) * Math.sqrt(tmp);
                System.out.println(V.matrix[i][0]);
                for (int j = i + 1; j < V.matrix.length; j++)
                    V.matrix[j][0] = A.matrix[j][i];
                System.out.print("v is ");
                V.printMatrix();
                System.out.println("-----");
                H = Matrix.difference(E, Matrix.multiply(1 / Matrix.multiply(V.transpose(), V).matrix[0][0], Matrix.multiply(2f, Matrix.multiply(V, V.transpose()))));

                Q = Matrix.multiply(Q, H);
                A = Matrix.multiply(H, A);
            }
            A = Matrix.multiply(A, Q);
            amount++;
        }
        System.out.println("===");
        V.printMatrix();

        System.out.println("===");
        H.printMatrix();
        System.out.println("===");
        Q.printMatrix();
        System.out.println("===");
        A.printMatrix();
        System.out.println(amount);
    }


    public static void main(String[] args) {
        solve();
    }

}
