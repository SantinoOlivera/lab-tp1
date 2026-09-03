public class Nodo {

    private Object elemento;
    private Nodo enlace;

    //Constructor
    public Nodo(Object elem, Nodo unEnlace) {
        //Constructor de Nodo.
        this.elemento = elem;
        this.enlace = unEnlace;
    }

    //Modificadores
    public void setElem(Object elem) {
        //Setea el elemento del nodo con el elemento ingresado.
        this.elemento = elem;
    }

    public void setEnlace(Nodo unEnlace) {
        //Setea el enlace del nodo con el nodo ingresado.
        this.enlace = unEnlace;
    }

    //Observadores
    public Object getElem() {
        //Devuelve el contenido/elemento del nodo.
        return this.elemento;
    }

    public Nodo getEnlace() {
        //Devuelve el enlace del nodo (otro nodo).
        return this.enlace;
    }
}
