package main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Inserire il numero di ruota da usare..");
        int numRuote = scan.nextInt();
        while (numRuote <= 0) {
            System.err.println("Inserisci un numero maggiore > 0");
            numRuote = scan.nextInt();
        }

        System.out.println("Inserisci l'ambo su cui vuoi puntare..");
        int primo, secondo;
        do {
            System.out.print("Primo numero[1-" + SharedData.MAX_NUMERO + "]: ");
            primo = scan.nextInt();
        } while (primo <= 0 || primo >= SharedData.MAX_NUMERO);
        do {
            System.out.print("Secondo numero[1-" + SharedData.MAX_NUMERO + "]: ");
            secondo = scan.nextInt();
        } while (secondo <= 0 || secondo >= SharedData.MAX_NUMERO);

        final SharedData sharedData = new SharedData(numRuote, primo, secondo);

        final ThGen thEstrai = new ThGen(sharedData);
        final ThCerca thCerca1 = new ThCerca(sharedData, 1), thCerca2 = new ThCerca(sharedData, 2);

        thEstrai.start();
        thCerca1.start();
        thCerca2.start();

        try {
            sharedData.getFineSemaphore().acquire(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        int ruoteVincenti = 0;
        for (Ruota r : sharedData.getRuote()) {
            if (r.hasWon()) {
                ruoteVincenti++;
                System.out.println("Ruota numero " + r.getNumRuota());
                System.out.println("Numeri estratti: " + Arrays.toString(r.getEstratti()));
            }
        }
        System.out.println("Hai vinto in " + ruoteVincenti + " ruote.");
    }
}
