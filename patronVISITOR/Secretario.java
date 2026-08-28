public class Secretario implements Empleado {

    private String nombre;
    private int dni;
    private double sueldo;
    private int horasExtra;

    public Secretario(String nombre, int dni, double sueldo, int horasExtra){
        this.nombre = nombre;
        this.dni = dni;
        this.sueldo = sueldo;
        this.horasExtra = horasExtra;
    }

    public void setSueldo(double sueldo){
        this.sueldo = sueldo;
    }

    public void setHorasExtra(int horas){
        this.horasExtra = horas;
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

    public int getHorasExtra(){
        return this.horasExtra;
    }

    public void aceptar(Visitor visitor){
        visitor.visitar(this);
    }
}