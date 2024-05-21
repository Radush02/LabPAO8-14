package org.example.lab11.ex1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureHelper {
    private static CompletableFutureHelper instance;

    private CompletableFutureHelper() {}

    public static synchronized CompletableFutureHelper getInstance() {
        if (instance == null) {
            instance = new CompletableFutureHelper();
        }
        return instance;
    }

    public void Helper(int nr, int thr) {
        Helper(nr, thr, true);
    }

    public void Helper(int nr, int thr, boolean print) {
        List<Integer> nrArr = new ArrayList<>();
        for (int i = 1; i <= nr; i++) {
            nrArr.add(i);
        }
        ExecutorService executor = Executors.newFixedThreadPool(thr);
        List<CompletableFuture<Void>> futures = getFutures(nr, thr, nrArr, print);
        CompletableFuture<Void> allOf =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();
        executor.shutdown();
    }

    private List<CompletableFuture<Void>> getFutures(
            int nr, int thr, List<Integer> nrArr, boolean print) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        int dim = (int) Math.ceil((double) nr / thr);
        for (int i = 0; i < thr; i++) {
            int start = i * dim;
            int fin = Math.min(start + dim, nr);
            if (start < fin) {
                List<Integer> subList = nrArr.subList(start, fin);
                CompletableFuture<Void> future =
                        CompletableFuture.runAsync(
                                () -> {
                                    for (int num : subList) {
                                        int p = (int) Math.pow(num, 2);
                                        if (print) System.out.println(num + "^" + 2 + " = " + p);
                                    }
                                });
                futures.add(future);
            }
        }
        return futures;
    }
}
