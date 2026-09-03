import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GestorAviones {
    private ScheduledExecutorService scheduler;
    private Avion[] arrAviones;
    private final int CANT_AVIONES;
    private final ScheduledFuture<?>[] tareasAviones;
    private int avionesTerminados;

    public GestorAviones(Avion[] arregloAviones) {
        this.scheduler = Executors.newScheduledThreadPool(3);
        this.arrAviones = arregloAviones;
        this.CANT_AVIONES = this.arrAviones.length;
        this.tareasAviones = new ScheduledFuture<?>[CANT_AVIONES];
        this.avionesTerminados = 0;
    }


    public void iniciar() {
        for (int i = 0; i < CANT_AVIONES; i++) {
            Avion avion = arrAviones[i];
            int indice = i;
            avion.despegar();

            tareasAviones[i] = scheduler.scheduleWithFixedDelay(
                () -> {
                        if (avion.avanzar()) {
                            tareasAviones[indice].cancel(false);
                            avionesTerminados++;

                            if (avionesTerminados == CANT_AVIONES) {
                                scheduler.shutdown();
                            }
                        }
                    },
                0, 
                1, 
                TimeUnit.SECONDS);
        }
    }

    public void detener() {
        scheduler.shutdown();
    }

}
