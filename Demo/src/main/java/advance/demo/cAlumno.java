/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advance.demo;

/**
 *
 * @author AJ
 */
public class cAlumno {
    private String Nombre;
    private String Apellido;
    private int Edad;
    private int DNI;
    private char Genero;
    private String Carrera;
    private String Semestre;

    public cAlumno(String nombre, String apellido, int edad, int dni, char genero, String carrera, String semestre) 
    {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Edad = edad;
        this.DNI = dni;
        this.Genero = genero;
        this.Carrera = carrera;
        this.Semestre = semestre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public char getGenero() {
        return Genero;
    }

    public void setGenero(char Genero) {
        this.Genero = Genero;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }

    
    
}
