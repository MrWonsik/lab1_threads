package invokeAnyAll;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Eval implements Callable<Integer> {

    Integer number;

    public Eval(Integer number){
        System.out.println("Tworze zadanie nr: " + number);
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Uruchamiam zadanie nr " + number);
        sleep(1000);
        System.out.println("Zakonczone zadanie nr " + number);
        return number;
    }
}
