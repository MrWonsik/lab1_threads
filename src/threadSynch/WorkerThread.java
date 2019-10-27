package threadSynch;

public class WorkerThread extends Thread {

    private Worker w;
    private int count;

    public WorkerThread(String name, Worker w, int count) {
        super(name);
        this.w = w;
        this.count = count;
        start();
    }

    public void run() {
        int wynik = 0;
        for (int i =0 ; i < count ; i++){
            wynik = w.work();
            if(wynik != 0) {
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + " kończy z wynikiem " + wynik);
    }
}
