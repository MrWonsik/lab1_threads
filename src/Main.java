import klasaThread.Timer;
import klasaThread.TimerRunnable;


public class Main {

    public static void main(String[] args) {
        Timer t = new Timer();
        t.start();

        TimerRunnable tr = new TimerRunnable();
        Thread[] array = {new Thread(tr), new Thread(tr), new Thread(tr)};

        for(Thread thread : array){
            thread.start();
        }

    }
}
