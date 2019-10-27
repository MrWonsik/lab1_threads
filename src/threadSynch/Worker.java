package threadSynch;

public class Worker {

    private int number = 0;

    public synchronized int work() {
        number++;
        number--;
        return number;
    }
}
