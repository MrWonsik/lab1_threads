package exc1;

import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class Reader {

    boolean endOfFile = false;
    private BufferedReader fileReader;
    private ReentrantLock lock = new ReentrantLock();

    public Reader(String pathToFile) {
        try {
            fileReader = new BufferedReader(new FileReader(pathToFile));
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        }
    }

    public void read() {
        lock.lock();
        try {
            for (int i = 0; i < 2; i++) {

                String line = fileReader.readLine();
                if(line == null){
                    endOfFile = true;
                } else {
                    System.out.print(Thread.currentThread().getName() + ": ");
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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
