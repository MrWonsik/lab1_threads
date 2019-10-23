package executor;

import static java.lang.Thread.sleep;

public class Task implements Runnable {

    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 1; i<=4; i++) {
            System.out.println(name + " " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();
        }
    }
}
