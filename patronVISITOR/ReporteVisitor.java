public class ReporteVisitor implements Visitor {
    
    public void visitar(Gerente gerente){
        System.out.println("=============================");
        System.out.println("     REPORTE DE EMPLEADO");
        System.out.println("=============================");
        System.out.println("Tipo: Gerente");
        System.out.println("Nombre: " + gerente.getNombre());
        System.out.println("DNI: "+ gerente.getDni());
        System.out.println("Sueldo base: "+ gerente.getSueldo());
        System.out.println("Empleados a cargo: "+ gerente.getEmpleados());
    }

    public void visitar(EmpleadoComun empleado){
        System.out.println("=============================");
        System.out.println("     REPORTE DE EMPLEADO");
        System.out.println("=============================");
        System.out.println("Tipo: Empleado comun");
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("DNI: "+ empleado.getDni());
        System.out.println("Sueldo base: "+ empleado.getSueldo());
        System.out.println("Antiguedad: "+ empleado.getAntiguedad());
    }

    public void visitar(Pasante pasante){
        System.out.println("=============================");
        System.out.println("     REPORTE DE EMPLEADO");
        System.out.println("=============================");
        System.out.println("Tipo: Pasante");
        System.out.println("Nombre: " + pasante.getNombre());
        System.out.println("DNI: "+ pasante.getDni());
        System.out.println("Sueldo base: "+ pasante.getHoras());
    }

    public void visitar(Secretario secretario){
        System.out.println("=============================");
        System.out.println("     REPORTE DE EMPLEADO");
        System.out.println("=============================");
        System.out.println("Tipo: Secretario");
        System.out.println("Nombre: " + secretario.getNombre());
        System.out.println("DNI: "+ secretario.getDni());
        System.out.println("Sueldo: "+ secretario.getSueldo());
        System.out.println("Horas extras: "+ secretario.getHorasExtra());
    }
}
