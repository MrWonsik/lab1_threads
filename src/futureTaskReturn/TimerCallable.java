package futureTaskReturn;

import java.util.concurrent.Callable;

public class TimerCallable implements Callable<Integer> {

    String name;
    int value = 0;

    public TimerCallable(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TimerCallable{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public Integer call() throws Exception {
        Integer k = 0;
        for (int i = 0; i < 4; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(this + " interrupted!");
                return null;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(this + " sleep() interrupted!");
                return null;
            }

            k += value * i;
        }
        System.out.println(this + " -> Wynik końcowy " + k);
        return k;
    }
}
