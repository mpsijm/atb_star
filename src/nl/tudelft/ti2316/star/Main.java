package nl.tudelft.ti2316.star;

/**
 * Created by theca on 24-11-2016.
 */
public class Main {

    public static void main(String[] args) {
        Matrix mat = new Matrix(new double[][]{{1, 2, 3}, {3, 2, 1}});
        Matrix mat2 = new Matrix(new double[][]{{1, 2}, {2, 3}, {3, 4}});
        System.out.println(mat);
        System.out.println(mat2);
        System.out.println(mat.multiply(mat2));
        System.out.println(mat2.multiply(mat));
        System.out.println(mat.transpose());
        System.out.println("----------------");

        Vector vec = new Vector(new double[]{4, 5, 6});
        System.out.println(mat);
        System.out.println(mat2);
        System.out.println(vec.asRow());
        System.out.println(mat.multiply(vec));
        System.out.println(mat.multiply(vec.asColumn()));
        System.out.println(vec.multiply(mat2));
        System.out.println(vec.asRow().multiply(mat2));
        System.out.println("----------------");

        System.out.println(vec.asRow().asVector());
        System.out.println(vec.asColumn().asVector());
        Vector v1 = new Vector(new double[]{1});
        System.out.println(v1.asRow().asVector());
        System.out.println(v1.asColumn().asVector());
    }

}
