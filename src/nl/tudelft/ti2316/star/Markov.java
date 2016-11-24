package nl.tudelft.ti2316.star;

/**
 * Created by theca on 24-11-2016.
 */
public class Markov {

    static Matrix markov = new Matrix(new double[][]{
            {0, .25, 0, .75},
            {.5, 0, 1./3., 1./6.},
            {0, 0, 1, 0},
            {0, .5, .25, .25},
    });

    static Vector probs = new Vector(new double[]{1, 0, 0, 0});

    static void simulate() {
        probs = probs.multiply(markov);
    }

    static void print(long i) {
        System.out.println(i + " " + probs);
    }

    public static void main(String[] args) {
        print(0);
        long i = 0;
        while (i < 3000) {
            i++;
            simulate();
            if (       (i <= 5)
                    || (i <= 25 && i % 5 == 0)
                    || (i <= 100 && i % 25 == 0)
                    || (i <= 5000 && i % 100 == 0))
                print(i);
        }
        System.out.println("Sumprob = " + probs.sum());
    }
}
