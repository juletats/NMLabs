package Lab2.Second;

import Matrix.Matrix;

public class IterationMethod {
    public static void main(String[] args) {
        new IterationMethod().solve();
    }
    public void solve() {
        float x1 = 0.25f;
        float x2 = 1.75f;

        float eps = 0.0001f;
        float eps_k = 1;
        int count = 0;
         Matrix Der_phi = der_phi(x1,x2);
        float q = max_der_phi(Der_phi);


        System.out.println(("\nSolving by iteration method:\n"));
        System.out.println(("-----------------------------"));
        System.out.println(("|     x1      |      x2     |"));

        while (eps_k > eps) {
          float  x1_k = phi1(x1, x2);
            float x2_k = phi2(x1, x2);

            count += 1;
           float tmp = Math.max(Math.abs(x1_k - x1), Math.abs(x1_k - x1));
            x1 = x1_k;
            x2 = x2_k;


            eps_k = (q) / (1 - q) * tmp;
            System.out.println("  "+x1_k + "       "+ x2_k);
        }
        System.out.println("---------------");
        System.out.println("Iterations: " + count);
    }

    public Matrix f(Matrix x) {
        Matrix a = new Matrix(2, 1, 0f);
        a.matrix[0][0] = f1(x.matrix[0][0], x.matrix[1][0]);
        a.matrix[1][0] = f2(x.matrix[0][0], x.matrix[1][0]);
        return a;
    }

    public float f1(float x1, float x2) {
        return x1 * x1 + x2 * x2 - 16;
    }

    public float f2(float x1, float x2) {
        return (float) (x1 - Math.exp(x2) + 4);
    }

    public float phi1(float x1, float x2) {
        return (float) Math.sqrt(16 - x2 * x2);
    }

    public float phi2(float x1, float x2) {
        // return (float) Math.exp(x2) + 4;
        return (float) Math.log(x1 + 4);
    }

    public float phi1_derx1(float x1, float x2) {
        return 0;
    }

    public float phi1_derx2(float x1, float x2) {
        return (float) (-(x2) / (Math.sqrt(9 - x2 * x2)));
    }

    public float phi2_derx1(float x1, float x2) {
        return 1 / (x1 + 3);
    }

    public float phi2_derx2(float x1, float x2) {
        return 0;
    }

    public Matrix der_phi(float x1, float x2) {
        Matrix a = new Matrix(2, 2, 0f);
        a.matrix[0][0] = phi1_derx1(x1, x2);
        a.matrix[0][1] = phi1_derx2(x1, x2);
        a.matrix[1][0] = phi2_derx1(x1, x2);
        a.matrix[1][1] = phi2_derx2(x1, x2);
        return a;
    }

    public float max_der_phi(Matrix Der_phi) {
        if( Math.abs(Der_phi.matrix[0][0]) + Math.abs(Der_phi.matrix[0][1]) >
                Math.abs(Der_phi.matrix[1][0]) + Math.abs(Der_phi.matrix[1][1]))
        return Math.abs(Der_phi.matrix[0][0]) + Math.abs(Der_phi.matrix[0][1]);
    else
        return Math.abs(Der_phi.matrix[1][0]) + Math.abs(Der_phi.matrix[1][1]);
    }
}

