package Lab1;

import Matrix.Matrix;

public class SeidelsMethod {

    private float[][] a = new float[][]{
            {12, -3, -1, 3},
            {5, 20,  9,1},
            {6, -3, -21, -7},
            {8, -7,3, -27}
    };
    public static int count =0;
    private float[] b = new float[]{-31,90,119,71};
    public Matrix SeidelSolve() {
    Matrix alpha = new Matrix(4,4,0);
        Matrix beta = new Matrix(4,1,0);
        Matrix x = new Matrix(4,1,0);
        Matrix xPrev =new Matrix(4,1,1);
        float epsilon = 0.001f;
        for (int i = 0; i < alpha.matrix.length; i++) {
            for (int j = 0; j < alpha.matrix[0].length; j++) {
                alpha.matrix[i][j] = -a[i][j] / (a[i][i]);
            }
        }
        for (int i = 0; i < 4; i++) {
            alpha.matrix[i][i]=0f;
        }
        for (int i = 0; i < 4; i++) {
            beta.matrix[i][0]=b[i]/a[i][i];
        }
        xPrev.copy(beta);
        for (int i = 0; i < beta.matrix.length; i++) {
            for (int j = 0; j < beta.matrix[0].length; j++) {
                xPrev.matrix[i][j] = beta.matrix[i][j];
            }
        }

        x = Matrix.sum(Matrix.multiply(alpha, xPrev), beta);
        float alphaNorm = alpha.norma();
        float k = alphaNorm / (1 - alphaNorm);

        while (k * Matrix.difference(x, xPrev).norma() > epsilon) {
            xPrev.copy(x);
            for (int i=0;i<x.matrix.length; i++) {
                x.matrix[i][0] = 0;
                for (int j=0;j<  x.matrix.length;j++)
                    x.matrix[i][0] += alpha.matrix[i][j] * x.matrix[j][0];
                x.matrix[i][0] += beta.matrix[i][0];
            }
            count++;
        }
        return  x;
}

    public static void main(String[] args) {
        SeidelsMethod seidelsMethod = new SeidelsMethod();
        seidelsMethod.SeidelSolve();
        System.out.println("Result is:");
        seidelsMethod.SeidelSolve().printMatrix();

        System.out.println("Amount of operations: "+ count);
    }
}
