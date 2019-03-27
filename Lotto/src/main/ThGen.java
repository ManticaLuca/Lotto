package main;

import java.util.Random;

public class ThGen extends Thread{
    private final SharedData sharedData;


    public ThGen(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        Random rn = new Random();
        for (Ruota r : sharedData.getRuote()) {
            for(int i =0;i<Ruota.NUMERI_PER_RUOTA;i++)
                r.setAt(i,rn.nextInt(SharedData.MAX_NUMERO));
            sharedData.getEstrattoUno().release();
            sharedData.getEstrattoDue().release();
        }
        sharedData.getFineSemaphore().release();
    }
}
