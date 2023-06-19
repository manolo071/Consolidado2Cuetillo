/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advance.demo;

/**
 *
 * @author AJ
 */
public class cAsignaturas {
    private String nombre;
    private String Semestre;
    private int numeroCreditos;
    
    public cAsignaturas(String nombre, String semestre, int numeroCreditos) {
        this.nombre = nombre;
        this.Semestre = semestre;
        this.numeroCreditos = numeroCreditos;
    }
    
    // Métodos getters y setters
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getNumeroCreditos() {
        return numeroCreditos;
    }
    
    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }
}

