import executor.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Executor exec = Executors.newFixedThreadPool(2);
        for(int i=1; i <= 4 ;i++){
            exec.execute(new Task("Task" + i));
        }
    }
}
