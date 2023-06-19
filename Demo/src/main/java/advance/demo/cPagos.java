/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advance.demo;

/**
 *
 * @author AJ
 */
public class cPagos {
    private String Alumno;
    private String TipoPago;
    private float Monto;
    private String MetodoPago;
    
     public cPagos(String alumno, String tipoPago, float monto, String metodoPago) {
        this.Alumno = alumno;
        this.TipoPago = tipoPago;
        this.Monto = monto;
        this.MetodoPago = metodoPago;
    }

    public String getAlumno() {
        return Alumno;
    }

    public void setAlumno(String Alumno) {
        this.Alumno = Alumno;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
    }
    
    
}
