package nl.tudelft.ti2316.star;

import java.util.Arrays;

/**
 * Created by theca on 24-11-2016.
 */
public class Matrix {

    double[][] mat;

    public Matrix(int m, int n) {
        mat = new double[m][n];
    }

    public Matrix(double[][] mat) {
        this.mat = mat;
        if (!checkDim()) throw new InconsistentMatrixException();
    }

    protected boolean checkDim() {
        if (this.mat.length == 0)
            return true;
        int n = this.mat[0].length;
        for (int i = 1; i < this.mat.length; i++) {
            if (this.mat[i].length != n)
                return false;
        }
        return true;
    }

    protected boolean sameDim(Matrix A) {
        return (this.mat.length == A.mat.length && this.mat[0].length == A.mat[0].length);
    }

    /** @return a random m-by-n matrix with values between 0 and 1 */
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = Math.random();
        return a;
    }

    /** @return n-by-n identity matrix I */
    public static double[][] identity(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }

    /** @return this^T */
    public Matrix transpose() {
        int m = this.mat.length;
        int n = this.mat[0].length;
        Matrix B = new Matrix(n, m);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                B.mat[j][i] = this.mat[i][j];
        return B;
    }

    /** @return this + A */
    public Matrix add(Matrix A) {
        int m = this.mat.length;
        int n = this.mat[0].length;
        if (!sameDim(A)) throw new IllegalMatrixDimensionsException(m, n, A.mat.length, A.mat[0].length);
        Matrix B = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                B.mat[i][j] = this.mat[i][j] + A.mat[i][j];
        return B;
    }

    /** @return this - A */
    public Matrix subtract(Matrix A) {
        int m = this.mat.length;
        int n = this.mat[0].length;
        if (!sameDim(A)) throw new IllegalMatrixDimensionsException(m, n, A.mat.length, A.mat[0].length);
        Matrix B = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                B.mat[i][j] = this.mat[i][j] - A.mat[i][j];
        return B;
    }

    /** @return this * A */
    public Matrix multiply(Matrix A) {
        int m1 = this.mat.length;
        int n1 = this.mat[0].length;
        int m2 = A.mat.length;
        int n2 = A.mat[0].length;
        if (n1 != m2) throw new IllegalMatrixDimensionsException(m1, n1, m2, n2);
        Matrix B = new Matrix(m1, n2);
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    B.mat[i][j] += this.mat[i][k] * A.mat[k][j];
        return B;
    }

    /** @return matrix-vector multiplication (A * x) */
    public Vector multiply(Vector x) {
        int m = this.mat.length;
        int n = this.mat[0].length;
        if (x.size() != n) throw new IllegalVectorDimensionsException(n, x.size());
        Vector y = new Vector(m);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y.data[i] += this.mat[i][j] * x.data[j];
        return y;
    }

    /** @return Vector of this matrix, if this matrix is 1xn or mx1 */
    public Vector asVector() {
        int m = this.mat.length;
        int n = this.mat[0].length;
        if (m == 1) {
            return new Vector(this.mat[0]);
        }
        if (n == 1) {
            Vector x = new Vector(m);
            for (int i = 0; i < x.size(); i++) {
                x.data[i] = this.mat[i][0];
            }
            return x;
        }
        throw new MatrixDimensionException("This matrix is no column or row matrix, but has dimensions " + m + "x" + n);
    }

    public double get(int i, int j) {
        return mat[i][j];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            sb.append("[ ").append(Arrays.toString(mat[i])).append(" ]\n");
        }
        return sb.toString();
    }
}

class MatrixDimensionException extends RuntimeException {
    MatrixDimensionException() {
        super();
    }

    MatrixDimensionException(String message) {
        super(message);
    }

    MatrixDimensionException(String message, Throwable cause) {
        super(message, cause);
    }

    MatrixDimensionException(Throwable cause) {
        super(cause);
    }
}

class IllegalMatrixDimensionsException extends MatrixDimensionException {
    public IllegalMatrixDimensionsException(int leftX, int leftY, int rightX, int rightY) {
        super("Cannot multiply dimensions " + leftX + "x" + leftY + " with " + rightX + "x" + rightY);
    }
}

class InconsistentMatrixException extends RuntimeException {
    public InconsistentMatrixException() {
        super("Inconsistent dimensions of double[][] array!");
    }
}
