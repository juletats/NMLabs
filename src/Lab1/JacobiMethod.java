package Lab1;

import Matrix.Matrix;

import static java.lang.Math.*;


public class JacobiMethod {
    public static int count = 0;
    float[][] task = new float[][]{
            {4, 7, -1},
            {7, -9, -6},
            {-1, -6, -4}
    };
    public Matrix Solve() {
        Matrix a = new Matrix(task);
       // System.out.println(a.matrix.length);
        //System.out.println(a.matrix[0].length);
        Matrix U = new Matrix(3, 1);
        float epsilon = 0.001f;
        float max;
        int l, k;
        float phi;
        Matrix u;
        while (a.symmetricalNorma() > epsilon) {
            max = 0f;
            l = 0;
            k = 0;
            for (int i = 0; i < a.matrix.length; i++)
                for (int j = i + 1; j < a.matrix[i].length; j++)
                    if (Math.abs(a.matrix[i][j]) > max) {
                        max = Math.abs(a.matrix[i][j]);
                        l = i;
                        k = j;
                    }
            u = new Matrix(3, 1f);
            phi = (float) (atan(2 * a.matrix[l][k] / (a.matrix[l][l] - a.matrix[k][k])) / 2);
            u.matrix[l][l] = (float) cos(phi);
            u.matrix[k][k] = (float) cos(phi);
            u.matrix[l][k] = (float) -sin(phi);
            u.matrix[k][l] = (float) sin(phi);

            a = Matrix.multiply(u.transpose(), a);

            a = Matrix.multiply(a, u);
            U = Matrix.multiply(U, u);

            count++;
        }
        System.out.println("Собственные значения:");
        for (int i=0;i<a.matrix.length;i++)
            System.out.printf("%.3f%n",a.matrix[i][i]);
        a.printMatrix();
        System.out.println("Собственный вектор 1:");
        Matrix SV1 = new Matrix(3,1,0);
        Matrix SV2 = new Matrix(3,1,0);
        Matrix SV3 = new Matrix(3,1,0);
        for (int i=0;i<U.matrix.length;i++) {
            System.out.printf("%.3f%n", U.matrix[i][0]);
            SV1.matrix[i][0] = U.matrix[i][0];
        }
        System.out.println("Собственный вектор 2:");
        for (int i=0;i<U.matrix.length;i++) {
            System.out.printf("%.3f%n", U.matrix[i][1]);
            SV2.matrix[i][0] = U.matrix[i][1];
        }
        System.out.println("Собственный вектор 3:");
        for (int i=0;i<U.matrix.length;i++){
            System.out.printf("%.3f%n",U.matrix[i][2]);
            SV3.matrix[i][0] = U.matrix[i][2];}
        System.out.println("-------------");
        SV1.printMatrix();
        System.out.println("-------------");
        SV2.printMatrix();
        System.out.println("-------------");
        SV3.printMatrix();
        System.out.println("-------------");
        //Matrix.Matrix m = new Matrix.Matrix(3,1,0) ;
        Matrix.multiply(SV1,SV2).printMatrix();
        Matrix.multiply(SV2,SV3).printMatrix();

        Matrix.multiply(SV1,SV3).printMatrix();

        System.out.println("-------------");
       //Matrix.Matrix.multiply(Matrix.Matrix.multiply(SV1,SV2),SV3).printMatrix();

        return U;
    }

    public static void main(String[] args) {
        JacobiMethod jm = new JacobiMethod();
        System.out.println("Result is:");
       jm.Solve();
        System.out.println("Количество итераций: "+ count);

    }
}
