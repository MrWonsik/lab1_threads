package futureTaskReturn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class OwnFutureTask<T> extends FutureTask<T> {
    String name;

    public OwnFutureTask(Callable<java.lang.Integer> callable) {
        super((Callable<T>) callable);
        name = callable.toString();
    }

    @Override
    protected void done() {
            T result = null;
            try {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(this + " interrupted");
                    return;
                }

                if (isCancelled()) {
                    System.out.println(this + " canceled!");
                    return;
                }
                result = this.get();
                System.out.println("Zadanie " + this + " -> wykonane: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    @Override
    public String toString() {
        return "OwnFutureTask{" +
                "name='" + name + '\'' +
                '}';
    }
}
