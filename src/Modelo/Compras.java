
package Modelo;


public class Compras {
    
    private int id;
    private int id_proveedor;
    private String nom_proveedor;
    private Double total;
    private String fecha;

    public Compras() {
    }

    public Compras(int id, int id_proveedor, String nom_proveedor, Double total, String fecha) {
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.nom_proveedor = nom_proveedor;
        this.total = total;
        this.fecha = fecha;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNom_proveedor() {
        return nom_proveedor;
    }

    public void setNom_proveedor(String nom_proveedor) {
        this.nom_proveedor = nom_proveedor;
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
