package org.example.lab12.ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class MyUtilityClass {
    public static <T> void printCollection(Collection<T> col) {
        if (col == null) {
            throw new IllegalArgumentException("Input-ul nu poate fi null");
        }
        System.out.println(col);
    }

    public static <T, R> R aggregate(Collection<T> col, R acc, BiFunction<R, T, R> met) {
        if (col == null || met == null) {
            throw new IllegalArgumentException("Input-ul nu poate fi null");
        }
        R rez = acc;
        for (T element : col) {
            rez = met.apply(rez, element);
        }
        return rez;
    }

    public static <T> void duplicateCollection(List<T> col) {
        if (col == null) {
            throw new IllegalArgumentException("Input-ul nu poate fi null");
        }
        List<T> aux = new ArrayList<>(col);
        col.clear();
        for (T i : aux) {
            col.add(i);
            col.add(i);
        }
    }
}
