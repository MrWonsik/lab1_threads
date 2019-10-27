package exc1;


import java.util.List;
import java.util.concurrent.Callable;

public class ReaderThread implements Callable<List<String>> {

    private Reader reader;

    public ReaderThread(Reader reader){
        this.reader = reader;
    }

    @Override
    public List<String> call() throws Exception {
        return reader.read();
    }
}
