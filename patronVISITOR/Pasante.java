public class Pasante implements Empleado {

    private String nombre;
    private int dni;
    private double valorHora;
    private int horasTrabajadas;

    public Pasante(String nombre, int dni, double valorHora, int horas){
        this.nombre = nombre;
        this.dni = dni;
        this.valorHora = valorHora;
        this.horasTrabajadas = horas;
    }

    public void setValorHora(double valorHora){
        this.sueldo = sueldo;
    }

    public void setHoras(int h){
        this.horasTrabajadas = h;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getDni(){
        return this.dni;
    }

    public double getValorHora(){
        return this.valorHora;
    }

    public int getHoras(){
        return this.horasTrabajadas;
    }

    public void aceptar(Visitor visitor){
        visitor.visitar(this);
    }
}