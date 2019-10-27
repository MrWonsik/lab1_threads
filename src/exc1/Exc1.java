package exc1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exc1 {
    public static void main(String[] args) {
        int threadCount = Integer.parseInt(args[0]);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        ArrayList<ReaderThread> readerThreads = new ArrayList<>();
        Reader reader = new Reader("./src/exc1/someFile.txt");
        for (int i = 0; i < threadCount; i++) {
            readerThreads.add(new ReaderThread(reader));
        }



        while(!reader.isEOF()) {
            for(ReaderThread readerThread : readerThreads){
                executor.execute(readerThread);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}