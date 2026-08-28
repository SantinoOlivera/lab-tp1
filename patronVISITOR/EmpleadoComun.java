import java.time.*;

public class EmpleadoComun implements Empleado {

    private String nombre;
    private int dni;
    private double sueldo;
    private int antiguedad;
    private int anoIngreso;

    public EmpleadoComun(String nombre, int dni, double sueldo, int anoIngreso){
        this.nombre = nombre;
        this.dni = dni;
        this.sueldo = sueldo;
        this.anoIngreso = anoIngreso;
        this.antiguedad = LocalDate.now().getYear() - anoIngreso;
    }

    public void setSueldo(double sueldo){
        this.sueldo = sueldo;
    }

    public void anoIngreso(int a){
        this.anoIngreso = a;
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

    public int getAñoIngreso(){
        return this.anoIngreso;
    }

    public int getAntiguedad() {
        return this.antiguedad;
    }

    public void aceptar(Visitor visitor){
        visitor.visitar(this);
    }
}