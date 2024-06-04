package org.example.lab13.task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CharCounter {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("java CharCounter <cale_fisier> <litera>");
            return;
        }
        String cale = args[0];
        char litera = args[1].charAt(0);
        AtomicInteger cnt = new AtomicInteger(0);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cale))) {
            String rand;
            while ((rand = br.readLine()) != null) {
                String finalRand = rand;
                CompletableFuture<Void> future =
                        CompletableFuture.runAsync(
                                () -> {
                                    int aparitii = 0;
                                    for (char c : finalRand.toCharArray())
                                        if (c == litera) aparitii++;

                                    cnt.addAndGet(aparitii);
                                },
                                executor);
                futures.add(future);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        CompletableFuture<Void> allOf =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }

        executor.shutdown();

        System.out.println("nr aparitii: " + cnt.get());
    }
}
