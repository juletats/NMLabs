package Lab1;

import Matrix.Matrix;

public class IterationMethod {
    private float[][] a = new float[][]{
            {12, -3, -1, 3},
            {5, 20, 9, 1},
            {6, -3, -21, -7},
            {8, -7, 3, -27}
    };
    private float[] b = new float[]{-31, 90, 119, 71};
    public  static  int count = 0;

    public static void main(String[] args) {
        IterationMethod iterationMethod = new IterationMethod();
        System.out.println("Result is:");
        iterationMethod.method().printMatrix();

        System.out.println("Amount of operations: "+ count);
    }


    public Matrix method() {
        float epsilon = 0.001f;
        Matrix x = new Matrix(4, 1, 0);
        Matrix xPrev = new Matrix(4, 1, 1);
        Matrix alpha = new Matrix(4, 4, 0);
        Matrix beta = new Matrix(4, 1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                alpha.matrix[i][j] = -a[i][j] / a[i][i];
            }
        }
        for (int i = 0; i < 4; i++) {
            alpha.matrix[i][i] = 0f;
        }
        for (int i = 0; i < 4; i++) {
            beta.matrix[i][0] = b[i] / a[i][i];
        }
        x = Matrix.sum(Matrix.multiply(alpha, xPrev), beta);

        float alphaNorma = alpha.norma();
        float k = alphaNorma / (1 - alphaNorma);

        while (k * Matrix.difference(x, xPrev).norma() > epsilon) {
            xPrev.copy(x);
            x = Matrix.multiply(alpha, x);
            x= Matrix.sum(x, beta);
            count++;
        }
        return x;
    }
}


