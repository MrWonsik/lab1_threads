package klasaThread;

public class Timer extends Thread {
    @Override
    public void run(){

        try {
            sleep(5000);
            System.out.println("Pracuje watek klasy Timer");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
