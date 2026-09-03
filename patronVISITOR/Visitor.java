public interface Visitor {
    void visitar(Gerente gerente);
    void visitar(EmpleadoComun empleado);
    void visitar(Pasante pasante);
    void visitar(Secretario secretario);
}



