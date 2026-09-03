public class Cola {

    private Nodo frente;
    private Nodo fin;

    //Constructor
    public Cola() {
        //Crea cola vacia.
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elemento) {
        //Pone el elemento al final de la cola.
        Nodo nuevoNodo = new Nodo(elemento, null);
        if (this.frente == null && this.fin == null) {
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        } else {
            this.fin.setEnlace(nuevoNodo);
            this.fin = nuevoNodo;
        }
        return true;
    }

    public boolean sacar() {
        /*Saca el frente y el frente pasa a ser el siguiente elemento. 
        Precondicion: Cola no vacia*/
        boolean frenteQuitado = false;
        if (!this.esVacia()) {
            if (this.frente.getEnlace() == null) {
                this.frente = null;
                this.fin = null;
            } else {
                this.frente = this.frente.getEnlace();
            }
            frenteQuitado = true;
        }
        return frenteQuitado;
    }

    public Object obtenerFrente() {
        //Devuelve el elemento que está en el frente. Precondición: la cola no está vacía.
        Object elemFrente = null;
        if (!this.esVacia()) {
            elemFrente = this.frente.getElem();
        }
        return elemFrente;
    }

    public boolean esVacia() {
        //Devuelve true si la cola no tiene elementos, false en caso contrario.
        return (this.frente == null);
    }

    @Override
    public String toString() {
        /*Crea y devuelve una cadena de caracteres formada por todos los 
        elementos de la cola para poder mostrarla por pantalla. Es recomendable 
        utilizar este método únicamente en la etapa de prueba y luego comentar el código.
         */
        String cadRetorno;
        if (this.frente == null) {
            cadRetorno = "Cola vacia";
        } else {
            cadRetorno = "[";
            Nodo aux = this.frente;
            while (aux != null) {
                cadRetorno = cadRetorno + aux.getElem();
                if (aux.getEnlace() != null) {
                    cadRetorno = cadRetorno + ",";
                }
                aux = aux.getEnlace();
            }
            cadRetorno = cadRetorno + "]";
        }
        return cadRetorno;
    }

    public void vaciar() {
        //Saca/elimina todos los elementos de la cola;
        this.frente = null;
        this.fin = null;
    }

    @Override
    public Cola clone() {
        /*Devuelve una copia exacta de los datos en la estructura original, y 
        respetando el orden de los mismos, en otra estructura del mismo tipo.*/
        Cola colaClon = new Cola();
        if (!this.esVacia()) {
            Nodo nodoAux = this.frente, nodoAux2;
            nodoAux2 = new Nodo(nodoAux.getElem(), null);
            colaClon.frente = nodoAux2;
            colaClon.fin = nodoAux2;
            while (nodoAux.getEnlace() != null) {
                Nodo nuevoNodo = new Nodo(nodoAux.getEnlace().getElem(), null);
                nodoAux2.setEnlace(nuevoNodo);
                nodoAux2 = nodoAux2.getEnlace();
                nodoAux = nodoAux.getEnlace();
                colaClon.fin = nuevoNodo;
            }

        }
        return colaClon;
    }

}
