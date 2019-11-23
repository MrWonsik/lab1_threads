package exc2;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Reservation {

    Random random = new Random();
    final Lock lock = new ReentrantLock();
    final Condition notFree = lock.newCondition();
    final Condition notReserved = lock.newCondition();

    ConcurrentHashMap<Integer, Boolean> pulaBiletow;

    Reservation(ConcurrentHashMap<Integer, Boolean> pulaBiletow) {
        this.pulaBiletow = pulaBiletow;
    }

    public void rezerwuj(String name, Integer rezerwowaneMiejsce) {
        synchronized (pulaBiletow) {
            lock.lock();
            try {

                while (pulaBiletow.get(rezerwowaneMiejsce)) {
                    System.out.println(LocalTime.now() + " " + name + " musi czekac na miejsce " + rezerwowaneMiejsce);
                    notFree.await();
                }
                System.out.println(LocalTime.now() + " " + name + " rezerwuje miejsce " + rezerwowaneMiejsce);
                pulaBiletow.replace(rezerwowaneMiejsce, false, true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


        try {
            Thread.sleep(1000 * random.nextInt(6));
            odwolaj(name, rezerwowaneMiejsce);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void odwolaj(String name, Integer odwolywaneMiejsce) {
        lock.lock();
        try {
            System.out.println(LocalTime.now() + " " + name + " zwalnia miejsce " + odwolywaneMiejsce);
            pulaBiletow.replace(odwolywaneMiejsce, true, false);
            notFree.signal();
        } finally {
            lock.unlock();
        }
    }

}
