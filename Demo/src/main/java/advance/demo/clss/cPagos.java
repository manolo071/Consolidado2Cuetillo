/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advance.demo.clss;

/**
 *
 * @author AJ
 */
public class cPagos {
    private String id;
    private String Alumno;
    private String TipoPago;
    private float Monto;
    private String MetodoPago;

    public cPagos(String id, String Alumno, String TipoPago, float Monto, String MetodoPago) {
        this.id = id;
        this.Alumno = Alumno;
        this.TipoPago = TipoPago;
        this.Monto = Monto;
        this.MetodoPago = MetodoPago;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
