package Matrix;

public class Matrix {
    public float[][] matrix;

    public Matrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int n, int m, float value) {
        matrix = new float[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = value;
            }
        }
    }

    public Matrix(int n, float diag) {
        matrix = new float[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i][i] = diag;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                String result = String.format("%.4f", matrix[i][j]);
                System.out.print(result + " ");
            }
            System.out.println();
        }
    }

    public static Matrix sum(Matrix a, Matrix b) {
        float[][] c = new float[a.matrix.length][a.matrix[0].length];
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix[0].length; j++) {
                c[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }
        Matrix C = new Matrix(c);
        return C;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.matrix.length, b.matrix[0].length, 0);
        for (int i = 0; i < a.matrix[0].length; i++) {
            for (int j = 0; j < b.matrix[0].length; j++) {
                for (int k = 0; k < a.matrix[0].length; k++)
                    c.matrix[i][j] += a.matrix[i][k] * b.matrix[k][j];
            }
        }
        return c;
    }

    public static Matrix multiply(float a, Matrix b) {
        Matrix c = new Matrix(b.matrix.length, b.matrix[0].length, 0);
        for (int i = 0; i < b.matrix.length; i++) {
            for (int j = 0; j < b.matrix[0].length; j++) {
                c.matrix[i][j] += b.matrix[i][j] * a;
            }
        }
        return c;
    }

    public static Matrix difference(Matrix a, Matrix b) {
        float[][] c = new float[a.matrix.length][a.matrix[0].length];
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix[0].length; j++) {
                c[i][j] = a.matrix[i][j] - b.matrix[i][j];
            }
        }
        Matrix C = new Matrix(c);
        return C;
    }

    public void copy(Matrix a) {
        //float[][]c = new float[a.matrix.length][a.matrix[0].length];
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix[0].length; j++) {
                matrix[i][j] = a.matrix[i][j];
            }
        }

    }

    public float norma() {
        float max = 0;
        float sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
    public float symmetricalNorma() {
        float res = 0f;
        for (int i=0;i<matrix.length;i++)
            for (int j=i + 1; j< matrix[i].length;j++)
        res += matrix[i][j] * matrix[i][j];
        return (float)Math.sqrt(res);
    }
    public Matrix transpose( ) {
        Matrix result = new Matrix(matrix[0].length, matrix.length, 0f);
        for (int i=0;i<matrix.length;i++)
            for (int j=0;j<matrix[i].length;j++)
                result.matrix[j][i] = matrix[i][j];
        return result;
    }
    public float triangleNorm(){
        float res = Math.abs(matrix[1][0]);
        for (int i=0;i< matrix.length-1;i++) {
            if (Math.abs(matrix[i + 1][i]) < res)
                res = Math.abs(matrix[i + 1][i]);
        }
        return res;
    }

}
