package main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThCerca extends Thread {

    private final SharedData sharedData;
    private final int numThread;

    public ThCerca(SharedData sharedData, int numThread) {
        this.sharedData = sharedData;
        this.numThread = numThread;
    }

    @Override
    public void run() {
        Random rn = new Random();
        final int daCercare;
        if (numThread == 1) {
            daCercare = sharedData.getPunatatoUno();
        } else {
            daCercare = sharedData.getPuntatoDue();
        }
        try {
            for (Ruota r : sharedData.getRuote()) {
                if (numThread == 1) {
                    sharedData.getEstrattoUno().acquire();
                } else {
                    sharedData.getEstrattoDue().acquire();
                }
                for (int i = 0; i < Ruota.NUMERI_PER_RUOTA; i++) {
                    if (daCercare == r.getAt(i)) {
                        if (numThread == 1) {
                            r.setVintoUno(true);
                        } else {
                            r.setVintoDue(true);
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
        }
        sharedData.getFineSemaphore().release();
    }
}
