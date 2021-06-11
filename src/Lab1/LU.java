package Lab1;

import java.util.Arrays;

public class LU {
    private float[][] matrix;
    private float[][] lower;
    private float[][] upper;

    public LU() {
        matrix = new float[][]{
                {-7, -9, 1, -9},
                {-6, -8, -5, 2},
                {-3, 6, 5, -9},
                {-2, 0, -5, -9}};
        lower = new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};
        upper = new float[matrix.length][matrix.length];
        upper = Arrays.stream(matrix).map(float[]::clone).toArray(float[][]::new);

    }

    public static void main(String[] args) {

        float[] b = new float[]{29,42,11,75};
        LU lu = new LU();
        lu.getLU();
        System.out.println("Matrix.Matrix is");
        lu.print(lu.matrix);
        System.out.println("Lower is");
        lu.print(lu.lower);
        System.out.println("upper is");
        lu.print(lu.upper);
        System.out.println("X is");
        lu.printO(lu.x(b));
        System.out.println("----------------");
        System.out.println("Reversed matrix:");
        lu.print(lu.reverse());
        System.out.println("A*A^(-1)");
        lu.print(lu.multiply(lu.matrix,lu.reverse()));
        System.out.println("Det is " + (lu.det(lu.lower)) * lu.det(lu.upper));
    }

    public void print(float[][] some) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String result = String.format("%.4f\t", some[i][j]);
                System.out.print(" " + result + " ");
            }
            System.out.println();
        }
    }

    public void printO(float[] b) {
        for (int i = 0; i < 4; i++) {
            String result = String.format("%.4f", b[i]);
            System.out.println(result + " ");
        }
        System.out.println();
    }

    public void getLU() {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                lower[j][i] = upper[j][i] / upper[i][i];
                for (int k = 0; k < 4; k++) {
                    upper[j][k] -= lower[j][i] * upper[i][k];
                }
            }
        }
    }

    public float[] z(float[] b) {
        float[] z = new float[4];
        z[0] = b[0];
        for (int i = 1; i < 4; i++) {
            float sum = 0;
            for (int j = 0; j < i; j++) {
                sum += lower[i][j] * z[j];
                z[i] = b[i] - sum;
            }
        }
        return z;
    }

    public float det(float[][] matrix) {
        float deter = 1;
        for (int i = 0; i < 4; i++) {
            deter *= matrix[i][i];
        }
        return deter;
    }

    public float[] x(float[] b) {
        float[] x = new float[4];
        float[] z = z(b);
        x[3] = z[3] / upper[3][3];
        for (int i = 2; i >= 0; i--) {
            float sum = 0;
            for (int j = i + 1; j < 4; j++) {
                sum += upper[i][j] * x[j];
                x[i] = (z[i] - sum) / upper[i][i];
            }
        }
        return x;
    }

    public float[][] reverse() {
        float[][]reversed = new float[4][4];
        float[] a1 = {1, 0, 0, 0};
        float[] a2 = {0, 1, 0, 0};
        float[] a3 = {0, 0, 1, 0};
        float[] a4 = {0, 0, 0, 1};
        a1=x(a1);
        a2=x(a2);
        a3=x(a3);
        a4=x(a4);
            for (int j = 0; j < 4; j++)
                reversed[j][0] =a1[j];
        for (int j = 0; j < 4; j++)
            reversed[j][1] =a2[j];
        for (int j = 0; j < 4; j++)
            reversed[j][2] =a3[j];
        for (int j = 0; j < 4; j++)
            reversed[j][3] =a4[j];


return reversed;
    }

    public float[][] multiply(float[][] a, float[][] b) {
        float[][] c = new float[(a.length)][b[0].length];
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++)
                    c[i][j] += a[i][k] * b[k][j];
            }
        }
        return c;
    }
}
