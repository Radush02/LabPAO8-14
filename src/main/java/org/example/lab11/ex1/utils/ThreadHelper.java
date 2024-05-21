package org.example.lab11.ex1.utils;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadHelper {
    private static ThreadHelper instance;
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadHelper.class);

    private ThreadHelper() {}

    public static synchronized ThreadHelper getInstance() {
        if (instance == null) {
            instance = new ThreadHelper();
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
        List<Thread> threads = getThreads(nr, thr, nrArr, print);
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error("Thread oprit", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private static List<Thread> getThreads(int nr, int thr, List<Integer> nrArr, boolean print) {
        List<Thread> threads = new ArrayList<>();
        int dim = (int) Math.ceil((double) nr / thr);
        for (int i = 0; i < thr; i++) {
            int start = i * dim;
            int fin = Math.min(start + dim, nr);
            if (start < fin) {
                List<Integer> subList = nrArr.subList(start, fin);
                Thread thread = new PowThread(subList, print);
                threads.add(thread);
            }
        }
        return threads;
    }
}
