import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestorAviones {
    private ScheduledExecutorService scheduler;
    private Avion[] arrAviones;
    private final int CANT_AVIONES;

    public GestorAviones(Avion[] arregloAviones) {
        this.scheduler = Executors.newScheduledThreadPool(3);
        this.arrAviones = arregloAviones;
        this.CANT_AVIONES = this.arrAviones.length;
    }


    public void iniciar() {
        for (int i = 0; i < CANT_AVIONES; i++) {
            Avion avion = arrAviones[i];
            scheduler.scheduleWithFixedDelay(() -> avion.avanzar(), 0, 1, TimeUnit.SECONDS);
        }
    }

    public void detener() {
        scheduler.shutdown();
    }

}
