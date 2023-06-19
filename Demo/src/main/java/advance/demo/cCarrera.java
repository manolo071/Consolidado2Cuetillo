/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advance.demo;

/**
 *
 * @author AJ
 */
public class cCarrera {
    private String Descripcion;
    private int Semestres;
    private float CostoCredito;

    public cCarrera(String descripcion, int semestres, float costoCredito) {
        this.Descripcion = descripcion;
        this.Semestres = semestres;
        this.CostoCredito = costoCredito;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getSemestres() {
        return Semestres;
    }

    public void setSemestres(int Semestres) {
        this.Semestres = Semestres;
    }

    public float getCostoCredito() {
        return CostoCredito;
    }

    public void setCostoCredito(float CostoCredito) {
        this.CostoCredito = CostoCredito;
    }

    
    
}
