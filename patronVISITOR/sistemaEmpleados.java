public class sistemaEmpleados {
    public static void main(String[] args){

        Gerente gerente = new Gerente(
            "Morena",
            40506070,
            2500000,
            10
        );

        EmpleadoComun empleado = new EmpleadoComun(
            "Juan",
            45004006,
            1400000,
            2021
        );

        Pasante pasante = new Pasante(
            "Paola",
            43789657,
            12000,
            68
        );

        Secretario secretario = new Secretario(
            "Ariel",
            39768456,
            900000,
            13
        );

        Visitor visitor = new CalcularSueldoVisitor();
        Visitor visitor2 = new ReporteVisitor();

        gerente.aceptar(visitor);
        empleado.aceptar(visitor);
        pasante.aceptar(visitor);
        secretario.aceptar(visitor);

        gerente.aceptar(visitor2);
        empleado.aceptar(visitor2);
        pasante.aceptar(visitor2);
        secretario.aceptar(visitor2);

    }
}