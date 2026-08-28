public class Avion {
    private String id;
    private Lista camino;
    private int indiceCiudad;
    private final int CANT_CIUDADES;

    public Avion(String elId, Lista listaCamino) {
        this.id = elId;
        //Recordar que la primer pos de la lista es 1, no 0
        this.indiceCiudad = 1;
        this.camino = listaCamino;
        this.CANT_CIUDADES = listaCamino.longitud();
    }

    public void despegar() {
        System.out.println(this.id + " esta despegando de " + (String) camino.recuperar(1));
    }

    public boolean avanzar() {
        boolean llego = false;
        indiceCiudad++;
            if (indiceCiudad < CANT_CIUDADES) {
            System.out.println(this.id + " se encuentra volando por " + (String) camino.recuperar(indiceCiudad));
        } else if (indiceCiudad == CANT_CIUDADES) {
            System.out.println(this.id + " acaba de aterrizar en " + (String) camino.recuperar(indiceCiudad));
            llego = true;
        }
        return llego;
    }

}
