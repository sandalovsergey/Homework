package main.java.edu.spbu.matrix;

/**
 * Created by Sergey Sandalov on 05.10.2016.
 */
public interface Matrix {
    Matrix mul(Matrix other);

    Matrix pmul(Matrix other) throws InterruptedException;

    @Override
    boolean equals(Object o);
}
