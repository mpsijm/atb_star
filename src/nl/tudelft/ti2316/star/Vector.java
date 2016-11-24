package nl.tudelft.ti2316.star;

import java.util.Arrays;

/**
 * Created by theca on 24-11-2016.
 */
public class Vector {

    double[] data;

    public Vector(int n) {
        data = new double[n];
    }

    public Vector(double[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    public int size() {
        return data.length;
    }

    /** @return this^T x */
    public double dot(Vector x) {
        if (this.size() != x.size()) throw new IllegalVectorDimensionsException(this.size(), x.size());
        double sum = 0.0;
        for (int i = 0; i < x.size(); i++)
            sum += this.data[i] * x.data[i];
        return sum;
    }

    /** @return vector-matrix multiplication (x^T * A) */
    public Vector multiply(Matrix A) {
        int m = A.mat.length;
        int n = A.mat[0].length;
        if (size() != m) throw new IllegalVectorDimensionsException(m, size());
        Vector y = new Vector(n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y.data[j] += A.mat[i][j] * data[i];
        return y;
    }

    /** @return column Matrix */
    public Matrix asColumn() {
        Matrix A = new Matrix(size(), 1);
        for (int i = 0; i < size(); i++) {
            A.mat[i][0] = data[i];
        }
        return A;
    }

    /** @return row Matrix */
    public Matrix asRow() {
        Matrix A = new Matrix(1, size());
        for (int i = 0; i < size(); i++) {
            A.mat[0][i] = data[i];
        }
        return A;
    }

    /** @return the sum of all the entries in this vector */
    public double sum() {
        double res = 0;
        for (int i = 0; i < size(); i++) {
            res += data[i];
        }
        return res;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}

class IllegalVectorDimensionsException extends MatrixDimensionException {
    public IllegalVectorDimensionsException(int expected, int given) {
        super("Expected dimension " + expected + ", but " + given + " given");
    }
}
