public class Lista {

    private Nodo cabecera;
    private int longitud;

    //Constructor
    public Lista() {
        //Crea y devuelve una Lista vacia.
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        /*Agrega el nuevo elemento en la posicion deseada. La cantidad de elementos
        de la lista se incrementa en uno. Retorna true si la posicion es valida
        (1<=pos<=longitud(lista)+1) y asi el elemento se inserta, falso en caso
        contrario*/
        boolean elemInsertado = true;
        if (pos < 1 || pos > this.longitud + 1) {
            elemInsertado = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevoNodo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
                this.longitud++;
            }
        }
        return elemInsertado;
    }

    public boolean eliminar(int pos) {
        /*Elimina el elemento de la posicion deseada. La cantidad de elementos de
        la lista decrece en uno. Retorna true, si la lista no es vacia y asi se puede
        eliminar el elemento, retorna false en caso contrario.*/
        boolean elemEliminado = false;
        if (pos >= 1 && pos <= this.longitud) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
                elemEliminado = true;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
                elemEliminado = true;
            }
        }
        return elemEliminado;
    }

    public Object recuperar(int pos) {
        //Devuelve el elemento de la posicion deseada. PRECONDICION: Posicion valida.
        Object elemPos = null;
        if (pos >= 1 && pos <= this.longitud) {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            if (pos != 1) {
                aux = aux.getEnlace();
            }
            elemPos = aux.getElem();
        }
        return elemPos;
    }

    public int localizar(Object elem) {
        /*Retorna la posicion de la lista donde se encuentra la primera ocurrencia
        del elemento. Retorna -1 en caso de no encontrarlo.*/
        int posRetorno = -1;
        if (!this.esVacia()) {
            boolean encontrado = false;
            Nodo aux = this.cabecera;
            int i = 1;
            while (i <= this.longitud && encontrado == false) {
                if (aux.getElem().equals(elem)) {
                    posRetorno = i;
                    encontrado = true;
                } else {
                    aux = aux.getEnlace();
                    i++;
                }
            }
        }
        return posRetorno;
    }

    public boolean pertenece(Object elem) {
        //Retorna true si el elemento pertenece a la lista y false en caso contrario.
        boolean pertenece = false;
        Nodo aux = this.cabecera;
        while (aux != null && !pertenece) {
            pertenece = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        return pertenece;
    }

    public int longitud() {
        //Retorna la cantidad de elementos de la lista.
        return this.longitud;
    }

    public boolean esVacia() {
        /*Retorna true si la Lista no contiene elementos (es vacia) y
        retorna false en caso contrario (tiene aunque sea un elemento).*/
        return (this.longitud == 0);
    }

    public void vaciar() {
        //Elimina todos los elementos de la Lista.
        this.cabecera = null;
        this.longitud = 0;
    }

    @Override
    public String toString() {
        //Devuelve una cadena de texto con todos los elementos de la Lista.
        String cadRetorno;
        if (this.esVacia()) {
            cadRetorno = "Lista vacia";
        } else {
            cadRetorno = "[";
            Nodo aux = this.cabecera;
            while (aux != null) {
                cadRetorno = cadRetorno + aux.getElem();
                aux = aux.getEnlace();
                if (aux != null) {
                    cadRetorno += ",";
                }
            }
            cadRetorno = cadRetorno + "]";
        }
        return cadRetorno;
    }

    @Override
    public Lista clone() {
        Lista listaClon = new Lista();
        if (!this.esVacia()) {
            listaClon.longitud = this.longitud;
            Nodo aux = this.cabecera;
            Nodo aux2 = new Nodo(aux.getElem(), null);
            listaClon.cabecera = aux2;
            while (aux.getEnlace() != null) {
                Nodo nodoTemp = new Nodo(aux.getEnlace().getElem(), null);
                aux2.setEnlace(nodoTemp);
                aux = aux.getEnlace();
                aux2 = aux2.getEnlace();
            }
        }
        return listaClon;
    }

    public void invertir() {
        //Modifica la lista para que los elementos aparezcan en orden invertido (recursivamente).
        if (this.longitud > 0) {
            invertirPriv(this.cabecera, null, 1);
        }
    }

    private void invertirPriv(Nodo unNodo, Nodo nodoAnterior, int posActual) {
        //Metodo privado y recursivo para el metodo de invertir.
        if (posActual == this.longitud) {
            this.cabecera = unNodo;
            unNodo.setEnlace(nodoAnterior);
        } else {
            invertirPriv(unNodo.getEnlace(), unNodo, posActual + 1);
            unNodo.setEnlace(nodoAnterior);
        }
    }

    public void eliminarApariciones(Object elem) {
        //Elimina todas las apariciones del elemento si es que existe en la lista.
        while (this.cabecera != null && this.cabecera.getElem().equals(elem)) {
            this.cabecera = this.cabecera.getEnlace();
            this.longitud--;
        }
        Nodo aux = this.cabecera.getEnlace(), antAux = this.cabecera;
        while (aux != null) {
            if (aux.getElem().equals(elem)) {
                antAux.setEnlace(aux.getEnlace());
                aux = antAux.getEnlace();
                this.longitud--;
            } else {
                antAux = aux;
                aux = aux.getEnlace();
            }
        }
    }

    public boolean moverAAnteultimaPosicion(int pos) {
        //Quita el nodo en ubicado en pos y lo mueve a la anteultima posicion.
        boolean movido = true;
        if (this.longitud == 0 || pos <= 0 || pos > this.longitud) {
            movido = false;
        } else {
            Nodo aux = this.cabecera;
            if (this.longitud == 1 || pos == this.longitud - 1) {
                movido = false;
            } else if (this.longitud == 2 && pos == 2) {
                Nodo aux2 = aux.getEnlace();
                aux2.setEnlace(aux);
                this.cabecera = aux2;
                aux.setEnlace(null);
            } else if (pos == this.longitud) {
                Nodo aux2 = this.cabecera;
                int i = 1;
                while (i < this.longitud - 1) {
                    aux = aux2;
                    aux2 = aux2.getEnlace();
                    i++;
                }
                aux.setEnlace(aux2.getEnlace());
                aux.getEnlace().setEnlace(aux2);
                aux2.setEnlace(null);
            } else {
                int i = 1;
                Nodo aux2 = this.cabecera;
                while (i < pos) {
                    aux = aux2;
                    aux2 = aux2.getEnlace();
                    i++;
                }
                aux.setEnlace(aux2.getEnlace());
                aux = aux2;
                while (i < this.longitud - 1) {
                    aux2 = aux2.getEnlace();
                    i++;
                }
                aux.setEnlace(aux2.getEnlace());
                aux2.setEnlace(aux);
            }
        }
        return movido;
    }

    public Lista obtenerMultiplos(int num) {
        /*Devuelve una lista con los elementos de la lista invocadora del metodo
        en las posiciones que son multiplos del numero pasado por parametro.*/
        Lista lis = new Lista();
        if (this.longitud != 0 && num > 0) {
            Nodo aux = this.cabecera, aux2 = this.cabecera;
            int i = 1;
            while (aux != null && i <= this.longitud) {
                if (i % num == 0) {
                    if (lis.cabecera == null) {
                        lis.cabecera = new Nodo(aux.getElem(), null);
                        aux2 = lis.cabecera;
                    } else {
                        aux2.setEnlace(new Nodo(aux.getElem(), null));
                        aux2 = aux2.getEnlace();
                    }
                    lis.longitud++;
                }
                aux = aux.getEnlace();
                i++;
            }
        }
        return lis;
    }

}
