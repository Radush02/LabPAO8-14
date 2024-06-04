package org.example.lab13.task5;

import java.util.ArrayList;
import java.util.List;

public class Bilant implements Comparable<Bilant>{
    private int pozitive;
    private int negative;

    /*
     * Avand in vedere ca pare a fi model de examen
     * banuiesc ca n-avem voie cu Lombok.
     */
    public Bilant(int pozitive, int negative) {
        this.pozitive = pozitive;
        this.negative = negative;
    }
    public Bilant(){
        this(0,0);
    }

    public int getPozitive() {
        return pozitive;
    }
    public void setPozitive(int pozitive) {
        this.pozitive = pozitive;
    }
    public int getNegative() {
        return negative;
    }
    public void setNegative(int negative) {
        this.negative = negative;
    }
    @Override
    public int compareTo(Bilant o) {
        return Integer.compare(this.pozitive - this.negative, o.pozitive - o.negative);
    }
    @Override
    public String toString() {
        return pozitive + "-" + negative;
    }
    public static void main(String[] args) {
        List<Bilant> bilanturi = new ArrayList<>();
        bilanturi.add(new Bilant());
        Bilant b = new Bilant();
        b.setPozitive(10);
        b.setNegative(5);
        bilanturi.add(b);

        bilanturi.add(new Bilant(7, 5));
        bilanturi.add(new Bilant(8, 5));

        bilanturi.stream()
                .sorted()
                .toList()
                .forEach(p -> System.out.println(p));
    }
}
