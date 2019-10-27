package threadSynch;

public class Main {

    public static void main(String[] args) {
        int tnum = Integer.parseInt(args[0]);
        int count = Integer.parseInt(args[1]);

        Worker w = new Worker();

        Thread[] thread = new Thread[tnum];
        for(int i =0 ; i < tnum ; i++){
            thread[i] = new WorkerThread("W" + (i + 1), w, count);
        }

        try {
            for (int i = 0; i < tnum; i++){
                thread[i].join();
            }
        } catch (InterruptedException exc){
            System.exit(1);
        }
    }

}

