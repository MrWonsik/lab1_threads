import executor.Task;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for(int i=1; i <= 10 ;i++){
            exec.execute(new Task("Task" + i));
        }
        Thread.yield();
        exec.shutdown();

        try {
            exec.execute(new Task("Task po zamknieciu"));
        } catch (RejectedExecutionException e)
        {
            e.printStackTrace();
        }
        try {
            exec.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(exec.isTerminated());

    }
}
