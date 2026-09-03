import java.util.HashMap;

/**
 *
 * @author Santino Olivera FAI-4520
 */
//Implementacion dinamica de grafo etiquetado con listas de adyacencia.
public class GrafoEtiquetadoLista {

    private NodoVert inicio;

    //Constructor
    public GrafoEtiquetadoLista() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoElem) {
        /*Dado un elemento se agrega este al grafo verificando que el elemento
        no se encuentre en el grafo. Si el elemento se inserto con exito el metodo
        devuelve true, en caso contrario devuelve false.*/
        boolean exito = false;
        NodoVert aux = this.encontrarVertice(nuevoElem);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoElem, this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object elElem) {
        /*Dado un elemento elimina el nodo que lo contiene en caso de que exista
        (y todos los arcos que lo tengan como origen o destino) y retorna true, 
        si el elemento no existe retorna false.*/
        boolean exito = false;
        NodoVert nodoEliminar = this.encontrarVertice(elElem);

        if (nodoEliminar != null) {
            NodoVert aux = this.inicio;
            while (aux != null) {
                erradicarArco(aux, nodoEliminar);
                aux = aux.getSigVertice();
            }
            if (this.inicio == nodoEliminar) {
                this.inicio = this.inicio.getSigVertice();
            } else {
                aux = this.encontrarAnterior(elElem);
                aux.setSigVertice(nodoEliminar.getSigVertice());
            }
            exito = true;
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, Object laEtiqueta) {
        /*Dado dos elementos que representan los nodos origen y destino se inserta 
        un arco entre estos el cual va a tener la etiqueta ingresada*/
        boolean exito = false;
        NodoVert nodo1 = this.encontrarVertice(origen);
        NodoVert nodo2 = this.encontrarVertice(destino);

        if (nodo1 != null && nodo2 != null && !this.verificarAdyacencia(nodo1, nodo2)) {
            this.insertarArcoPriv(nodo1, nodo2, laEtiqueta);
            this.insertarArcoPriv(nodo2, nodo1, laEtiqueta);
            exito = true;
        }
        return exito;
    }

    private void insertarArcoPriv(NodoVert origen, NodoVert destino, Object laEtiqueta) {
        /*Agrega un nuevo nodo en los nodos adyacentes del nodo origen, al nuevo nodo
        se le guarda la etiqueta ingresada y apuntara al nodo destino.*/
        if (origen.getPrimerAdy() == null) {
            origen.setPrimerAdy(new NodoAdy(destino, laEtiqueta));
        } else {
            NodoAdy nuevoAdy = new NodoAdy(destino, laEtiqueta);
            nuevoAdy.setSigAdyacente(origen.getPrimerAdy());
            origen.setPrimerAdy(nuevoAdy);
        }
    }

    public boolean eliminarArco(Object origen, Object destino) {
        /*Dado los elementos de los extremos de un arco, elimina al arco que los une.
        Si la operacion se realizo correctamente retorna true, en caso contrario
        retorna false.*/
        boolean exito = false;
        NodoVert extremo1 = this.encontrarVertice(origen);
        NodoVert extremo2 = this.encontrarVertice(destino);

        if (extremo1 != null && extremo2 != null && this.verificarAdyacencia(extremo1, extremo2)) {
            this.erradicarArco(extremo1, extremo2);
            this.erradicarArco(extremo2, extremo1);
            exito = true;
        }
        return exito;
    }

    public boolean existeVertice(Object elem) {
        //Dado un elemento, verifica si este existe en el grafo (como vertice).
        boolean existe = false;
        NodoVert buscado = this.encontrarVertice(elem);
        if (buscado != null) {
            existe = true;
        }
        return existe;
    }

    public Object buscarRetornar(Object elem) {
        //Dado un elemento, lo busca y lo retorna si es que existe
        NodoVert buscado = this.encontrarVertice(elem);
        Object elemBuscado = null;
        if (buscado != null) {
            elemBuscado = buscado.getElem();
        }
        return elemBuscado;
    }

    public boolean existeArco(Object origen, Object destino) {
        /*Dado dos elementos, verifica si existe un arco entre estos en el grafo,
        si es asi retorna true y false en caso contrario (no existe el arco,
        no existe alguno de los nodos o ambos).*/
        boolean existe;
        NodoVert nodo1 = this.encontrarVertice(origen);
        NodoVert nodo2 = this.encontrarVertice(destino);
        existe = nodo1 != null && nodo2 != null && this.verificarAdyacencia(nodo1, nodo2);
        return existe;
    }

    public boolean existeCamino(Object origen, Object destino) {
        /*Dado dos elementos, verifica si existe al menos un camino desde
        el origen hasta el destino y retorna true si cumple, en caso contrario
        (no existe alguno de los nodos o ambos o no hay camino) retorna false.*/
        boolean exito;
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        Lista lis = new Lista();

        exito = nodoOrigen != null && nodoDestino != null && this.existeCaminoPriv(nodoOrigen, nodoDestino, lis);
        return exito;
    }

    private boolean existeCaminoPriv(NodoVert vertAux, NodoVert destino, Lista lisVisitados) {
        /*Recorre el grafo en profundidad desde origen hasta encontrar a destino,
        si lo encuentra retorna true y false en caso contrario (no hay camino).*/
        boolean verifica = false;
        if (vertAux != null) {
            lisVisitados.insertar(vertAux.getElem(), lisVisitados.longitud() + 1);
            NodoAdy adyAux = vertAux.getPrimerAdy();
            while (adyAux != null && !verifica) {
                if (adyAux.getVertice() == destino) {
                    verifica = true;
                } else if (lisVisitados.localizar(adyAux.getVertice().getElem()) < 0) {
                    verifica = existeCaminoPriv(adyAux.getVertice(), destino, lisVisitados);
                }
                adyAux = adyAux.getSigAdyacente();
            }
        }
        return verifica;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        /*Dado dos elementos que representan vertices, se retorna una lista de vertices
        que ilustra el camino mas corto entre estos dos vertices, en caso de que no exista
        el camino o alguno de los dos vertices no exista (o ambos) se retorna una lista vacia.
        Aclaracion: Si hay mas de un camino (del mismo tamaño) se retorna el primero en encontrar.*/
        Lista caminoCorto = new Lista();
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            if (nodoOrigen == nodoDestino) {
                caminoCorto.insertar(nodoOrigen.getElem(), 1);
            } else {
                caminoCorto = caminoMasCortoPriv(nodoOrigen, nodoDestino);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoPriv(NodoVert origen, NodoVert destino) {
        /*Recorre el grafo en anchura empezando desde origen hasta encontrar
        el nodo destino buscando el camino mas corto, retornando el camino en 
        una lista, si no encuentra camino retorna lista vacia.*/
        HashMap<NodoVert, NodoVert> predecesor = new HashMap<>();
        boolean encontrado = false;
        Lista caminoCorto = new Lista();
        Lista lisVisitados = new Lista();
        if (origen != null) {
            Cola cola = new Cola();
            lisVisitados.insertar(origen.getElem(), lisVisitados.longitud() + 1);
            cola.poner(origen);
            while (!cola.esVacia() && !encontrado) {
                NodoVert aux = (NodoVert) cola.obtenerFrente();
                cola.sacar();
                if (aux == destino) {
                    encontrado = true;
                } else {
                    NodoAdy adyAux = aux.getPrimerAdy();
                    while (adyAux != null) {
                        if (lisVisitados.localizar(adyAux.getVertice().getElem()) < 0) {
                            lisVisitados.insertar(adyAux.getVertice().getElem(), lisVisitados.longitud() + 1);
                            cola.poner(adyAux.getVertice());
                            predecesor.put(adyAux.getVertice(), aux);
                        }
                        adyAux = adyAux.getSigAdyacente();
                    }
                }
            }
            if (encontrado) {
                NodoVert aux = destino;
                while (aux != null) {
                    caminoCorto.insertar(aux.getElem(), 1);
                    aux = predecesor.get(aux);
                }
            }
        }
        return caminoCorto;
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        /*Dado dos elementos que representan vertices, se retorna una lista de vertices
        que ilustra el camino mas largo entre estos dos vertices, en caso de que no exista
        el camino o alguno de los dos vertices no exista (o ambos) se retorna una lista vacia.
        Aclaracion: Si hay mas de un camino (del mismo tamaño) se retorna el primero en encontrar.*/
        Lista caminoLargo = new Lista();
        NodoVert nodoOrigen = this.encontrarAnterior(origen);
        NodoVert nodoDestino = this.encontrarAnterior(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            if (nodoOrigen == nodoDestino) {
                caminoLargo.insertar(origen, 1);
            } else {
                caminoLargo = caminoMasLargoPriv(nodoOrigen, nodoDestino, new Lista(), new Lista());
            }
        }
        return caminoLargo;

    }

    private Lista caminoMasLargoPriv(NodoVert nodoActual, NodoVert destino, Lista caminoActual, Lista mejorCamino) {
        /*Recorre el grafo en profundidad empezando desde origen hasta encontrar
        el nodo destino buscando el camino mas largo, retornando el camino en 
        una lista, si no encuentra camino retorna lista vacia.*/
        //Mejorar
        if (nodoActual != null) {
            caminoActual.insertar(nodoActual.getElem(), caminoActual.longitud() + 1);
            if (nodoActual == destino) {
                if (caminoActual.longitud() > mejorCamino.longitud()) {
                    mejorCamino = caminoActual.clone();
                }
            } else {
                NodoAdy adyAux = nodoActual.getPrimerAdy();
                while (adyAux != null) {
                    if (!caminoActual.pertenece(adyAux.getVertice().getElem())) {
                        mejorCamino = caminoMasLargoPriv(adyAux.getVertice(), destino, caminoActual, mejorCamino);
                    }
                    adyAux = adyAux.getSigAdyacente();
                }
            }
            caminoActual.eliminar(caminoActual.longitud());
        }
        return mejorCamino;
    }

    public Lista listarEnProfundidad() {
        /*Lista los elementos de el grafo recorridos en profundidad y retorna la lista*/
        Lista listaVisitados = new Lista();
        NodoVert verticeAux = this.inicio;
        while (verticeAux != null) {
            if (listaVisitados.localizar(verticeAux.getElem()) < 0) {
                this.listarProfundidadPriv(verticeAux, listaVisitados);
            }
            verticeAux = verticeAux.getSigVertice();
        }
        return listaVisitados;
    }

    private void listarProfundidadPriv(NodoVert nodo, Lista lisVisitados) {
        //Metodo privado para el metodo de listar grafo en profundidad.
        if (nodo != null) {
            lisVisitados.insertar(nodo.getElem(), lisVisitados.longitud() + 1);
            NodoAdy adyAux = nodo.getPrimerAdy();
            while (adyAux != null) {
                if (lisVisitados.localizar(adyAux.getVertice().getElem()) < 0) {
                    listarProfundidadPriv(adyAux.getVertice(), lisVisitados);
                }
                adyAux = adyAux.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        //Lista los elementos de el grafo reccoridos en anchura y retorna la lista.
        Lista listaVisitados = new Lista();
        NodoVert verticeAux = this.inicio;
        while (verticeAux != null) {
            if (listaVisitados.localizar(verticeAux.getElem()) < 0) {
                this.listarEnAnchuraPriv(verticeAux, listaVisitados);
            }
            verticeAux = verticeAux.getSigVertice();
        }
        return listaVisitados;
    }

    private void listarEnAnchuraPriv(NodoVert nodo, Lista lisVisitados) {
        //Metodo privado para el metodo de listar grafo en anchura.
        if (nodo != null) {
            Cola cola = new Cola();
            lisVisitados.insertar(nodo.getElem(), lisVisitados.longitud() + 1);
            cola.poner(nodo);
            while (!cola.esVacia()) {
                NodoVert aux = (NodoVert) cola.obtenerFrente();
                cola.sacar();
                NodoAdy adyAux = aux.getPrimerAdy();
                while (adyAux != null) {
                    if (lisVisitados.localizar(adyAux.getVertice().getElem()) < 0) {
                        lisVisitados.insertar(adyAux.getVertice().getElem(), lisVisitados.longitud() + 1);
                        cola.poner(adyAux.getVertice());
                    }
                    adyAux = adyAux.getSigAdyacente();
                }
            }
        }
    }

    public boolean esVacio() {
        //Retorna falso si hay al menos un vertice en el grafo y true en caso contrario
        return this.inicio == null;
    }

    @Override
    public GrafoEtiquetadoLista clone() {
        /*Retorna un grafo el cual es equivalente en nodos y contenido 
        al que llama al metodo.*/
        //Se tomo que no importa que el orden de los vertices y adyacentes no importa.
        GrafoEtiquetadoLista grafoClon = new GrafoEtiquetadoLista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            grafoClon.insertarVertice(aux.getElem());
            aux = aux.getSigVertice();
        }

        aux = this.inicio;
        while (aux != null) {
            NodoAdy adyAux = aux.getPrimerAdy();
            while (adyAux != null) {
                grafoClon.insertarArco(aux.getElem(), adyAux.getVertice().getElem(), adyAux.getEtiqueta());
                adyAux = adyAux.getSigAdyacente();
            }
            aux = aux.getSigVertice();
        }

        return grafoClon;
    }

    @Override
    public String toString() {
        /*Retorna una cadena String la cual muestra los vertices almacenados
        en el grafo y los adyacentes de cada uno.*/
        String cad = "";
        if (this.inicio == null) {
            cad = "Grafo vacio";
        } else {
            NodoVert actual = this.inicio;
            while (actual != null) {
                cad += actual.getElem().toString() + " ---> ";
                NodoAdy aux = actual.getPrimerAdy();

                if (aux == null) {
                    cad += "Sin adyacentes";
                } else {
                    cad += aux.getVertice().getElem().toString() + " (" + aux.getEtiqueta() + ") ";
                    aux = aux.getSigAdyacente();
                    while (aux != null) {
                        cad += ", " + aux.getVertice().getElem().toString() + " (" + aux.getEtiqueta() + ") ";
                        aux = aux.getSigAdyacente();
                    }
                }
                actual = actual.getSigVertice();
                cad += "\n";
            }
        }
        return cad;
    }

    public Lista caminoMasCortoEtiqueta(Object origen, Object destino) {
        /*Dado dos elementos que representan vertices (origen y destino), se 
        retorna una lista de vertices que ilustra el camino mas corto basandose 
        en las etiquetas y no en nodos entre estos dos vertices, en caso de que no exista
        el camino o alguno de los dos vertices no exista (o ambos) se retorna una lista vacia.
        Aclaracion: Si hay mas de un camino (del mismo tamaño) se retorna el primero en encontrar.*/
        Lista caminoCorto = new Lista();
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            if (nodoOrigen == nodoDestino) {
                caminoCorto.insertar(nodoOrigen.getElem(), 1);
            } else {
                caminoCorto = caminoMasCortoEtiquetaPriv(nodoOrigen, nodoDestino);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoEtiquetaPriv(NodoVert origen, NodoVert destino) {
        /*Metodo privado para el metodo de camino mas corto basandose en las etiquetas.
        Aclaracion: Se uso el algoritmo de "Dijkstra" para resolver este metodo.*/
        //Usar profundidad, modificar camino mas largo.
        HashMap<NodoVert, Integer> distancias = new HashMap<>();
        HashMap<NodoVert, NodoVert> predecesor = new HashMap<>();
        Lista lisVisitados = new Lista(), lisCamino = new Lista();

        NodoVert vertAux = this.inicio;
        while (vertAux != null) {
            distancias.put(vertAux, Integer.MAX_VALUE);
            vertAux = vertAux.getSigVertice();
        }
        distancias.put(origen, 0);

        NodoVert vertActual = origen;
        NodoAdy adyAux;

        while (vertActual != null && vertActual != destino) {
            lisVisitados.insertar(vertActual, 1);
            adyAux = vertActual.getPrimerAdy();

            while (adyAux != null) {

                if ((!lisVisitados.pertenece(adyAux.getVertice())) && ((int) distancias.get(vertActual) + (int) adyAux.getEtiqueta()) < distancias.get(adyAux.getVertice())) {
                    distancias.put(adyAux.getVertice(), (int) distancias.get(vertActual) + (int) adyAux.getEtiqueta());
                    predecesor.put(adyAux.getVertice(), vertActual);
                }
                adyAux = adyAux.getSigAdyacente();
            }

            vertActual = obtenerMenorNoVisitado(distancias, lisVisitados);
        }

        while (vertActual != null) {
            lisCamino.insertar(vertActual.getElem(), 1);
            vertActual = predecesor.get(vertActual);
        }

        return lisCamino;
    }

    public Lista caminoMasCortoEtiquetaDFS(Object origen, Object destino) {
        Lista caminoCorto = new Lista();
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        int[] mejorPeso = {Integer.MAX_VALUE};
        if (nodoOrigen != null && nodoDestino != null) {
            caminoCorto = caminoMasCortoEtiquetaDFSPriv(nodoOrigen, nodoDestino, new Lista(), new Lista(), 0, mejorPeso);
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoEtiquetaDFSPriv(NodoVert actual, NodoVert destino, Lista caminoActual, Lista mejorCamino, int pesoActual, int[] mejorPeso) {
        /*Metodo privado para el metodo de camino mas corto basandose en etiquetas.
        En este caso hizo con DFS.*/
        caminoActual.insertar(actual.getElem(), caminoActual.longitud() + 1);
        if (actual == destino) {
            if (pesoActual < mejorPeso[0]) {
                mejorCamino = caminoActual.clone();
                mejorPeso[0] = pesoActual;
            }
        } else {
            NodoAdy adyAux = actual.getPrimerAdy();
            while (adyAux != null) {
                if (!caminoActual.pertenece(adyAux.getVertice()) && ((int) adyAux.getEtiqueta() + pesoActual) < mejorPeso[0]) {
                    mejorCamino = caminoMasCortoEtiquetaDFSPriv(adyAux.getVertice(), destino, caminoActual, mejorCamino, ((int) adyAux.getEtiqueta() + pesoActual), mejorPeso);
                }
                adyAux = adyAux.getSigAdyacente();
            }
        }
        caminoActual.eliminar(caminoActual.longitud());
        return mejorCamino;
    }

    public Lista caminoMasCortoEtiquetaDFSSinVertice(Object origen, Object destino, Object prohibido) {
        Lista caminoCorto = new Lista();
        int[] mejorPeso = {Integer.MAX_VALUE};
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        NodoVert nodoProhibido = this.encontrarVertice(prohibido);
        if (nodoOrigen != null && nodoDestino != null && nodoOrigen != nodoProhibido && nodoDestino != nodoProhibido) {
            if (nodoOrigen == nodoDestino) {
                caminoCorto.insertar(nodoOrigen.getElem(), 1);
            } else if (nodoProhibido != null) {
                caminoCorto = caminoMasCortoEtiquetaDFSSinVerticePriv(nodoOrigen, nodoDestino, nodoProhibido, new Lista(), new Lista(), 0, mejorPeso);
            } else {
                caminoCorto = caminoMasCortoEtiquetaPriv(nodoOrigen, nodoDestino);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoEtiquetaDFSSinVerticePriv(NodoVert actual, NodoVert destino, NodoVert prohibido, Lista caminoActual, Lista mejorCamino, int pesoActual, int[] mejorPeso) {
        /*Metodo privado para el metodo de camino mas corto basandose en etiquetas.
        En este caso hizo con DFS.*/
        caminoActual.insertar(actual.getElem(), caminoActual.longitud() + 1);
        if (actual == destino) {
            if (pesoActual < mejorPeso[0]) {
                mejorCamino = caminoActual.clone();
                mejorPeso[0] = pesoActual;
            }
        } else {
            NodoAdy adyAux = actual.getPrimerAdy();
            while (adyAux != null) {
                if (!caminoActual.pertenece(adyAux.getVertice()) && ((int) adyAux.getEtiqueta() + pesoActual) < mejorPeso[0] && adyAux.getVertice() != prohibido) {
                    mejorCamino = caminoMasCortoEtiquetaDFSSinVerticePriv(adyAux.getVertice(), destino, prohibido, caminoActual, mejorCamino, ((int) adyAux.getEtiqueta() + pesoActual), mejorPeso);
                }
                adyAux = adyAux.getSigAdyacente();
            }
        }
        caminoActual.eliminar(caminoActual.longitud());
        return mejorCamino;
    }

    public Lista caminoMasCortoEtiquetaSinVertice(Object origen, Object destino, Object prohibido) {
        /*Dado tres elementos que representan vertices (origen, destino y prohibido), se retorna
        una lista de vertices que ilustra el camino mas corto basandose en el etiquetado que va
        desde el vertice origen al vertice destino pero que no pasa por el vertice prohibido.
        En caso de que no exista alguno de los vertices origen o destino (o ambos) se retorna
        lista vacia.*/
        Lista caminoCorto = new Lista();
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        NodoVert nodoProhibido = this.encontrarVertice(prohibido);
        if (nodoOrigen != null && nodoDestino != null && nodoOrigen != nodoProhibido && nodoDestino != nodoProhibido) {
            if (nodoOrigen == nodoDestino) {
                caminoCorto.insertar(nodoOrigen.getElem(), 1);
            } else if (nodoProhibido != null) {
                caminoCorto = caminoMasCortoEtiquetaSinVerticePriv(nodoOrigen, nodoDestino, nodoProhibido);
            } else {
                caminoCorto = caminoMasCortoEtiquetaPriv(nodoOrigen, nodoDestino);
            }
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoEtiquetaSinVerticePriv(NodoVert origen, NodoVert destino, NodoVert prohibido) {
        /*Metodo privado para el metodo de camino mas corto basandose en las etiquetas y
        donde no se pasa por una vertice especificado.
        Aclaracion: Se uso el algoritmo de "Dijkstra" para resolver este metodo.*/
        HashMap<NodoVert, Integer> distancias = new HashMap<>();
        HashMap<NodoVert, NodoVert> predecesor = new HashMap<>();
        Lista lisVisitados = new Lista(), lisCamino = new Lista();

        NodoVert vertAux = this.inicio;
        while (vertAux != null) {
            distancias.put(vertAux, Integer.MAX_VALUE);
            vertAux = vertAux.getSigVertice();
        }
        distancias.put(origen, 0);

        lisVisitados.insertar(prohibido, 1);

        NodoVert vertActual = origen;
        NodoAdy adyAux;

        while (vertActual != null && vertActual != destino) {
            lisVisitados.insertar(vertActual, 1);
            adyAux = vertActual.getPrimerAdy();

            while (adyAux != null) {

                if ((!lisVisitados.pertenece(adyAux.getVertice())) && ((int) distancias.get(vertActual) + (int) adyAux.getEtiqueta()) < distancias.get(adyAux.getVertice())) {
                    distancias.put(adyAux.getVertice(), (int) distancias.get(vertActual) + (int) adyAux.getEtiqueta());
                    predecesor.put(adyAux.getVertice(), vertActual);
                }
                adyAux = adyAux.getSigAdyacente();
            }

            vertActual = obtenerMenorNoVisitado(distancias, lisVisitados);
        }

        while (vertActual != null) {
            lisCamino.insertar(vertActual.getElem(), 1);
            vertActual = predecesor.get(vertActual);
        }

        return lisCamino;
    }

    public Lista caminosPosibles(Object origen, Object destino) {
        /*Dado dos elementos que representan vertices (origen y destino), se retorna
        una lista de vertices que contiene todos los caminos posibles entre estos dos
        vertices. En caso de que no exista alguno de los vertices origen o destino
        (o ambos) o que no exista ni un camino se retorna lista vacia.*/
        Lista listaCaminos = new Lista();
        NodoVert nodoOrigen = this.encontrarVertice(origen);
        NodoVert nodoDestino = this.encontrarVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            if (nodoOrigen == nodoDestino) {
                listaCaminos.insertar(origen, 1);
            } else {
                caminosPosiblesPriv(nodoOrigen, nodoDestino, new Lista(), listaCaminos, new Lista());
            }
        }
        return listaCaminos;
    }

    private void caminosPosiblesPriv(NodoVert nodoActual, NodoVert destino, Lista caminoActual, Lista listaCaminos, Lista lisVisitados) {
        /*Metodo privado para el metodo de mostrar todos los caminos posibles 
        entre dos ciudades.*/
        if (nodoActual != null) {
            caminoActual.insertar(nodoActual.getElem(), caminoActual.longitud() + 1);
            lisVisitados.insertar(nodoActual.getElem(), lisVisitados.longitud() + 1);

            if (nodoActual == destino) {
                listaCaminos.insertar(caminoActual.clone(), 1);
            } else {
                NodoAdy adyAux = nodoActual.getPrimerAdy();
                while (adyAux != null) {
                    if (!lisVisitados.pertenece(adyAux.getVertice().getElem())) {
                        caminosPosiblesPriv(adyAux.getVertice(), destino, caminoActual, listaCaminos, lisVisitados);
                    }
                    adyAux = adyAux.getSigAdyacente();
                }
            }
            caminoActual.eliminar(caminoActual.localizar(nodoActual.getElem()));
            lisVisitados.eliminar(lisVisitados.localizar(nodoActual.getElem()));
        }
    }

    //Metodos internos (privados) de la estructura que se usaron en mas de un metodo.
    private NodoVert encontrarVertice(Object elem) {
        /*Dado un elemento busca el nodo (vertice) que lo contiene y lo retorna
        en caso de encontrarlo, en caso contrario retorna null.*/
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(elem)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    private NodoVert encontrarAnterior(Object unElem) {
        /*Dado un elemento, si este existe en el grafo, retorna el nodo que esta
        antes de este, si el elemento no existe retorna null.*/
        NodoVert aux = this.inicio;
        while (aux != null && aux.getSigVertice() != null && !aux.getSigVertice().getElem().equals(unElem)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    private void erradicarArco(NodoVert origen, NodoVert destino) {
        //Dado dos nodos, elimina el arco que van desde origen a destino.
        NodoAdy actual = origen.getPrimerAdy();
        NodoAdy anterior = null;
        boolean erradicado = false;

        while (actual != null && !erradicado) {
            if (actual.getVertice() == destino) {
                if (anterior == null) {
                    origen.setPrimerAdy(actual.getSigAdyacente());
                } else {
                    anterior.setSigAdyacente(actual.getSigAdyacente());
                }
                erradicado = true;
            } else {
                anterior = actual;
            }
            actual = actual.getSigAdyacente();
        }
    }

    private boolean verificarAdyacencia(NodoVert origen, NodoVert destino) {
        /*Verifica si existe un arco entre los nodos ingresados (adyacentes), si
        existe retorna true y false en caso contrario.*/
        boolean adyacentes = false;
        NodoAdy aux = origen.getPrimerAdy();

        while (aux != null) {
            if (aux.getVertice() == destino) {
                adyacentes = true;
                aux = null;
            } else {
                aux = aux.getSigAdyacente();
            }
        }
        return adyacentes;
    }

    private NodoVert obtenerMenorNoVisitado(HashMap<NodoVert, Integer> hashDistancias, Lista listaVisitados) {
        /*Metodo privado usado en el metodo privado para encontrar el camino mas corto (segun etiquetado) y en el 
        metodo privado para encontrar camino mas corto (segun etiquetado) que no pase por un vertice especifico.*/
        NodoVert menor = null, vertAux = this.inicio;
        int menorDistancia = Integer.MAX_VALUE;
        while (vertAux != null) {
            if ((!listaVisitados.pertenece(vertAux)) && ((int) hashDistancias.get(vertAux) < menorDistancia)) {
                menor = vertAux;
                menorDistancia = (int) hashDistancias.get(vertAux);
            }
            vertAux = vertAux.getSigVertice();
        }
        return menor;
    }

}
