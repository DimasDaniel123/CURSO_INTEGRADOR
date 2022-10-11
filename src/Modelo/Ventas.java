
package Modelo;

public class Ventas {
    private int id;
    private int id_cliente;
    private String nom_cliente;
    private Double total;
    private String fecha;

    public Ventas() {
    }

    public Ventas(int id, int id_cliente, String nom_cliente, Double total, String fecha) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.nom_cliente = nom_cliente;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
