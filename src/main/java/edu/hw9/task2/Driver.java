package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;

public class Driver {

    public static void main(String[] params){
        launch("C://",10_000);
    }

    public static void launch(String rootDirectory, int minFilesInDirectory){
        AsyncDFS dfs = new AsyncDFS(Path.of(rootDirectory),minFilesInDirectory);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(dfs));
    }

}
