package executor;

import static java.lang.Thread.sleep;

public class Task implements Runnable {

    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {


        try {
            sleep(1000);
            System.out.println(name);
        } catch (InterruptedException e) {
            System.out.println("Przerwano!");
        }
        Thread.yield();
    }

}
