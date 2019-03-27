package main;

public class Ruota {
    public static final int NUMERI_PER_RUOTA = 5;

    private final int numRuota;
    private int[] estratti;
    private boolean vintoUno, vintoDue;

    public Ruota(int numRuota) {
        this.numRuota = numRuota;
        estratti = new int[NUMERI_PER_RUOTA];
        vintoUno = false;
        vintoDue = false;
    }

    public synchronized int getNumRuota() {
        return numRuota;
    }

    public synchronized void setAt(int index, int val) {
        estratti[index] = val;
    }

    public synchronized int getAt(int index) {
        return estratti[index];
    }

    public synchronized boolean isVintoUno() {
        return vintoUno;
    }

    public synchronized void setVintoUno(boolean vintoUno) {
        this.vintoUno = vintoUno;
    }

    public synchronized boolean isVintoDue() {
        return vintoDue;
    }

    public synchronized void setVintoDue(boolean vintoDue) {
        this.vintoDue = vintoDue;
    }

    public synchronized boolean hasWon() {
        return vintoUno && vintoDue;
    }
    
    public int[] getEstratti() {
        return estratti;
    }
}
