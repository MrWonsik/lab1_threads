package exc2;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exc2 {

    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ConcurrentHashMap<Integer, Boolean> listaBiletow = new ConcurrentHashMap<>();
        listaBiletow.put(1, false);
        listaBiletow.put(2, false);
        listaBiletow.put(3, false);

        Reservation reservation = new Reservation(listaBiletow);

        for(int i=0; i<20; i++) {
            executorService.execute(new ClientThread("Client" + i, random.nextInt(3) + 1, reservation));
        }

        executorService.shutdown();


    }
}
