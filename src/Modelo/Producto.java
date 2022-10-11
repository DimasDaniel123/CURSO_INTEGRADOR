
package Modelo;


public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private int id_proveedor;
    private int stock;
    private double precio;
    private double precioV;
    private int id_categoria;
    private String proveedor;
    private String cat;

    public Producto() {
    }

    public Producto(int id, String codigo, String nombre, int id_proveedor, int stock, double precio, double precioV, int id_categoria, String proveedor, String cat) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_proveedor = id_proveedor;
        this.stock = stock;
        this.precio = precio;
        this.precioV = precioV;
        this.id_categoria = id_categoria;
        this.proveedor = proveedor;
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    
    
    

    

    
    
    
    
}
