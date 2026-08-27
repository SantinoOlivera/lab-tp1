public class CalcularSueldoVisitor implements Visitor {

    public void visitar(Gerente gerente) {
        double sueldo = gerente.getSueldo() + gerente.getEmpleados() * 20000;
        System.out.println(gerente.getNombre() + " cobra $" + sueldo);
    }

    public void visitar(EmpleadoComun empleado) {
        double sueldo  = empleado.getSueldo() + empleado.getAntiguedad() * 30000;
        System.out.println(empleado.getNombre() + "cobra $" + sueldo);
    }

    public void visitar(Pasante pasante) {
        double sueldo = pasante.getHoras() * pasante.getValorHora();
        System.out.println(pasante.getNombre() + "cobra $" + sueldo);
    }

    public void visitar(Secretario secretario) {
        double sueldo = secretario.getSueldo() + secretario.getHorasExtra() * 10000;
        System.out.println(secretario.getNombre() + "cobra $" + sueldo);
    }
}