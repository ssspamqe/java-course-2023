package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;

public class Driver {

    public static void main(String[] params){
        AsyncDFS dfs = new AsyncDFS(Path.of("C://"),10000);

        System.out.println(new Date());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(dfs));
        System.out.println(new Date());

    }

}
