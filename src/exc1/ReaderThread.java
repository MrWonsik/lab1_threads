package exc1;


public class ReaderThread extends Thread {

    private Reader reader;

    public ReaderThread(Reader reader){
        this.reader = reader;
        start();
    }

    public void run(){
            reader.read();

    }
}
