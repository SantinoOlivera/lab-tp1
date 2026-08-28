public class NodoAdy {

    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    //Constructor
    public NodoAdy(NodoVert elVertice, Object laEtiqueta) {
        this.vertice = elVertice;
        this.sigAdyacente = null;
        this.etiqueta = laEtiqueta;
    }

    //Modificadores
    public void setVertice(NodoVert elVertice) {
        this.vertice = elVertice;
    }

    public void setSigAdyacente(NodoAdy elSigAdyacente) {
        this.sigAdyacente = elSigAdyacente;
    }

    public void setEtiqueta(Object laEtiqueta) {
        this.etiqueta = laEtiqueta;
    }

    //Observadores
    public NodoVert getVertice() {
        return this.vertice;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public Object getEtiqueta() {
        return this.etiqueta;
    }

}
