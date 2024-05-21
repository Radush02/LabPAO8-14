package org.example.lab11.ex1;

import java.util.stream.IntStream;
import org.example.lab11.ex1.utils.CompletableFutureHelper;
import org.example.lab11.ex1.utils.ThreadHelper;

public class Main {
    public static void main(String[] args) {
        int nr = 10000;
        int thr = Runtime.getRuntime().availableProcessors();
        int pow = 2;
        // Thread
        ThreadHelper threadHelper = ThreadHelper.getInstance();
        threadHelper.Helper(nr, thr);

        // parallelStream
        IntStream.rangeClosed(1, nr)
                .parallel()
                .forEach(
                        n -> {
                            int p = (int) Math.pow(n, pow);
                            System.out.println(n + "^" + pow + " = " + p);
                        });

        // CompletableFuture
        CompletableFutureHelper completableFutureHelper = CompletableFutureHelper.getInstance();
        completableFutureHelper.Helper(nr, thr);
    }
}
