package klasaThread;

import static java.lang.Thread.sleep;

public class TimerRunnable implements Runnable {

    @Override
    public void run() {

        try {
            sleep(2000);
            System.out.println("Pracuje watek klasy TimerRunnable");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
