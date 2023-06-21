package advance.demo.clss;

import java.time.LocalDate;

public class cAlumno {
    private String id;
    private String Nombre;
    private String Apellido;
    private int Edad;
    private int DNI;
    private char Genero;
    private String Carrera;
    private String Semestre;
    private LocalDate Birthday;

    public cAlumno(String id, String nombre, String apellido, int edad, int dni, char genero, String carrera, String semestre) {
        this.id = id;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Edad = edad;
        this.DNI = dni;
        this.Genero = genero;
        this.Carrera = carrera;
        this.Semestre = semestre;
        this.Birthday = LocalDate.of(1999, 2, 21); // Datos iniciales ficticios para pruebas
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        this.Edad = edad;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDni(int dni) {
        this.DNI = dni;
    }

    public char getGenero() {
        return Genero;
    }

    public void setGenero(char genero) {
        this.Genero = genero;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String carrera) {
        this.Carrera = carrera;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String semestre) {
        this.Semestre = semestre;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.Birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getFirstNameProperty() {
//        return Nombre;
//    }
//
//    public String getLastNameProperty() {
//        return Apellido;
//    }
    

}
