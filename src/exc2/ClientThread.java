package exc2;


import java.util.Scanner;

public class ClientThread implements Runnable {

    String name;
    Integer miejsce;
    Reservation reservation;


    ClientThread(String name, Integer miejsce, Reservation reservation){
        this.name = name;
        this.miejsce = miejsce;
        this.reservation = reservation;

    }

    @Override
    public void run() {
            reservation.rezerwuj(name, miejsce);

    }
}
