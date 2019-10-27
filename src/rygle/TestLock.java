package rygle;

import java.util.concurrent.TimeUnit;

public class TestLock {
    public static void main(String[] args) {

        final TabString st = new TabString(new String[] { "koń", "kot", "pies"});
        new Thread ( new Runnable() {
            public void run() {
                st.set(3,"krowa");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("mineły 2 sek. - Wątek 2 działa");
                System.out.println(st.get(0));
                System.out.println("Wątek 2 sie kończy");
            }
        }).start();
    }
}
