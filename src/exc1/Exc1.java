package exc1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Exc1 {
    public static void main(String[] args) {
        int threadCount = Integer.parseInt(args[0]);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        ArrayList<ReaderThread> readerThreads = new ArrayList<>();
        ArrayList<Future<List<String>>> readerResults = new ArrayList<>();

        Reader reader = new Reader("./src/exc1/someFile.txt");
        for (int i = 0; i < threadCount; i++) {
            readerThreads.add(new ReaderThread(reader));
        }



        while(!reader.isEOF()) {
            for(ReaderThread readerThread : readerThreads){
                readerResults.add(executor.submit(readerThread));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        executor.shutdown();
        try {
            executor.awaitTermination( 1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reader.closeFile();

        for(Future<List<String>> result : readerResults) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

}