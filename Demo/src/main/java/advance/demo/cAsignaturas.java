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
    private String Descripcion;
    private String Semestre;
    private int numeroCreditos;
    
    public cAsignaturas(String descripcion, String semestre, int numeroCreditos) {
        this.Descripcion = descripcion;
        this.Semestre = semestre;
        this.numeroCreditos = numeroCreditos;
    }
    
    // Métodos getters y setters
    
    public String getDescripcion() {
        return Descripcion;
    }
    
    public void setDescripcion(String nombre) {
        this.Descripcion = nombre;
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

