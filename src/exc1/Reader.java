package exc1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Reader {

    private boolean endOfFile = false;
    private BufferedReader fileReader;
    private ReentrantLock lock = new ReentrantLock();

    public Reader(String pathToFile) {
        try {
            fileReader = new BufferedReader(new FileReader(pathToFile));
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        }
    }

    public synchronized List<String> read() {
        List<String> stringsList = new ArrayList<>();
        lock.lock();
        try {
            for (int i = 0; i < 2; i++) {

                String line = fileReader.readLine();
                if(line == null){
                    endOfFile = true;
                } else {
                    stringsList.add(Thread.currentThread() + ": " + line);
                }
            }
            return stringsList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return stringsList;
    }

    public void closeFile() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEOF() {
        return endOfFile;
    }
}
