import futureTaskReturn.OwnFutureTask;
import futureTaskReturn.TimerCallable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<OwnFutureTask<Integer>> futureArrayList = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(1);


        for (int i = 0; i < 10; i++) {
            futureArrayList.add(new OwnFutureTask<>(new TimerCallable(String.valueOf(i), random.nextInt(50))));
        }

        for(OwnFutureTask<Integer> ownFutureTask : futureArrayList){
            exec.execute(ownFutureTask);
        }

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println("PULE WATKOW!");
//
//        Method[] methods = ThreadPoolExecutor.class.getDeclaredMethods();
//        for (Method method : methods) {
//            String name = method.getName();
//            if (name.startsWith("get")) {
//                Object result = null;
//                try {
//                    result = method.invoke(exec);
//                } catch (Exception e) {
//                    System.out.println("Nie moge wywołać metody: " + name + " !");
//                }
//                System.out.println(name + " = " + result);
//            }
//        }

        ((ThreadPoolExecutor) exec).setMaximumPoolSize(8);
        ((ThreadPoolExecutor) exec).setCorePoolSize(8);

        futureArrayList.get(0).cancel(true);
        futureArrayList.get(1).cancel(false);
        futureArrayList.get(7).cancel(true);

        exec.shutdown();

        try {
            exec.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("Executor servise isTerminated: " + exec.isTerminated());
        System.out.println("Uruchomiono zadań: " + futureArrayList.size());

//        for (Method method : methods) {
//            String name = method.getName();
//            if (name.startsWith("get")) {
//                Object result = null;
//                try {
//                    result = method.invoke(exec);
//                } catch (Exception e) {
//                    System.out.println("Nie moge wywołać metody: " + name + " !");
//                }
//                System.out.println(name + "=" + result);
//            }
//        }
    }


}
