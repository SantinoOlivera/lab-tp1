public class Gerente implements Empleado {

    private String nombre;
    private int dni;
    private double sueldo;
    private int empleadosACargo;

    public Gerente(String nombre, int dni, double sueldo, int empleados){
        this.nombre = nombre;
        this.dni = dni;
        this.sueldo = sueldo;
        this.empleados = empleados;
    }

    public void setSueldo(double sueldo){
        this.sueldo = sueldo;
    }

    public void setEmpleados(int em){
        this.empleados = em;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getDni(){
        return this.dni;
    }

    public double getSueldo(){
        return this.sueldo;
    }

    public int getEmpleados(){
        return this.empleados;
    }

    public void aceptar(Visitor visitor){
        visitor.visitar(this);
    }
}