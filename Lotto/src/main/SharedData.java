package main;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class SharedData {

    public static final int MAX_NUMERO = 99;
    private Vector<Ruota> ruote;
    private final int numRuote, punatatoUno, puntatoDue;
    private final Semaphore estrattoUno, estrattoDue, fineSemaphore;

    public SharedData(int numRuote, int punatatoUno, int puntatoDue) {
        this.numRuote = numRuote;
        this.punatatoUno = punatatoUno;
        this.puntatoDue = puntatoDue;
        ruote = new Vector<>();
        for (int i = 0; i < numRuote; i++) {
            ruote.add(new Ruota(i));
        }
        estrattoDue = new Semaphore(0);
        estrattoUno = new Semaphore(0);
        fineSemaphore = new Semaphore(0);
    }

    public synchronized Semaphore getEstrattoUno() {
        return estrattoUno;
    }

    public synchronized Semaphore getEstrattoDue() {
        return estrattoDue;
    }

    public synchronized int getPunatatoUno() {
        return punatatoUno;
    }

    public synchronized int getPuntatoDue() {
        return puntatoDue;
    }

    public synchronized Vector<Ruota> getRuote() {
        return ruote;
    }

    public synchronized Semaphore getFineSemaphore() {
        return fineSemaphore;
    }
}
