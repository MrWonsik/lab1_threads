package rygle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TabString {
    private String[] txt;
    private Lock lock = new ReentrantLock();

    public TabString(String[] txt) {
        this.txt = txt;
    }

    public void set(int i, String s) {
        lock.lock();
        try {
            txt[i] = s;
        } finally {
            lock.unlock();
        }
    }

    public String get(int i) {
        String t = null;
        lock.lock();
        t = txt[i];
        lock.unlock();
        return t;
    }
}
