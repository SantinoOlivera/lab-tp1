public class NodoVert {

    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    //Constructor
    public NodoVert(Object unElem) {
        this.elem = unElem;
        this.sigVertice = null;
        this.primerAdy = null;
    }

    public NodoVert(Object unElem, NodoVert elSigVertice) {
        this.elem = unElem;
        this.sigVertice = elSigVertice;
        this.primerAdy = null;
    }

    //Modificadores
    public void setElem(Object unElem) {
        this.elem = unElem;
    }

    public void setSigVertice(NodoVert elSigVertice) {
        this.sigVertice = elSigVertice;
    }

    public void setPrimerAdy(NodoAdy elPrimerAdy) {
        this.primerAdy = elPrimerAdy;
    }

    //Observadores
    public Object getElem() {
        return this.elem;
    }

    public NodoVert getSigVertice() {
        return this.sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }
}