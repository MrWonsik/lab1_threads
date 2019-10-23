package invokeAnyAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestInvoke {

    public static int sum(ExecutorService exec, List<Callable<Integer>> tasks) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Start!");
        List<Future<Integer>> result = exec.invokeAll(tasks);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Koniec po " + elapsed / 1000 + " sec.");
        int suma = 0;
        for (Future<Integer> r : result) {
            suma += r.get();
        }
        return suma;
    }

    public static void main(String[] args) {
        List<Callable<Integer>> taskList = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 20; i++) {
            Callable<Integer> task = new Eval(i);
            taskList.add(task);
        }
        try{
            int result = sum(exec, taskList);
            System.out.println("Wynik: " + result);
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
