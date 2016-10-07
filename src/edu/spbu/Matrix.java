package edu.spbu;

/**
 * Created by Sergey Sandalov on 05.10.2016.
 */
public interface Matrix {
    Matrix mul (Matrix other);

    @Override
    boolean equals(Object o);
}
