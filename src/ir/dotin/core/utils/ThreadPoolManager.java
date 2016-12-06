package ir.dotin.core.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;


public class ThreadPoolManager {

    public static AtomicLong sessionCount = new AtomicLong(0);
    public static ExecutorService executor = Executors.newFixedThreadPool(200);
    public static ExecutorService executors = Executors.newCachedThreadPool();
    public static final ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    public static Integer CYCLE_NUMBER;
}