package org.example.lab11.ex2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import org.example.lab11.ex1.utils.CompletableFutureHelper;
import org.example.lab11.ex1.utils.ThreadHelper;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> nrArr = new ArrayList<>();
        Collections.addAll(nrArr, 10, 1000, 10000, (int) 1e7);

        int thr = Runtime.getRuntime().availableProcessors();
        int pow = 2;
        try (BufferedWriter res =
                new BufferedWriter(
                        new FileWriter("src/main/java/org/example/lab11/ex2/rezultate.txt"))) {
            for (int nr : nrArr) {
                runThread(res, nr, thr);
                runParallelStream(res, nr, pow);
                runCompletableFuture(res, nr, thr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runThread(BufferedWriter res, int nr, int thr) throws IOException {
        ThreadHelper threadHelper = ThreadHelper.getInstance();
        long start = System.currentTimeMillis();
        threadHelper.Helper(nr, thr, false); // System.out.println() consuma mult timp, il ignoram.
        long end = System.currentTimeMillis();
        res.write("Thread: " + (end - start) + " ms" + " @ " + nr + " numere\n");
        res.flush();
    }

    private static void runParallelStream(BufferedWriter res, int nr, int pow) throws IOException {
        long start = System.currentTimeMillis();
        IntStream.rangeClosed(1, nr)
                .parallel()
                .forEach(
                        n -> {
                            Math.pow(n, pow);
                        });
        long end = System.currentTimeMillis();
        res.write("parallelStream: " + (end - start) + " ms" + " @ " + nr + " numere\n");
        res.flush();
    }

    private static void runCompletableFuture(BufferedWriter res, int nr, int thr)
            throws IOException {
        CompletableFutureHelper completableFutureHelper = CompletableFutureHelper.getInstance();
        long start = System.currentTimeMillis();
        completableFutureHelper.Helper(
                nr, thr, false); // System.out.println() consuma mult timp, il ignoram.
        long end = System.currentTimeMillis();
        res.write("CompletableFuture: " + (end - start) + " ms" + " @ " + nr + " numere\n");
        res.flush();
    }
}
