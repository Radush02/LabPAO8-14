package org.example.lab11.ex1.utils;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
class PowThread extends Thread {
    private final List<Integer> nrArr;
    private final int pow;
    private final boolean print;

    public PowThread(List<Integer> nrArr, int pow) {
        this.nrArr = nrArr;
        this.pow = pow;
        this.print = true;
    }

    public PowThread(List<Integer> nrAdd,boolean print){
        this(nrAdd,2,print);
    }
    @Override
    public void run() {
        for (int num : nrArr) {
            int p = (int) Math.pow(num, pow);
            if (print)
                System.out.println(num + "^" + pow + " = " + p);
        }
    }
}
