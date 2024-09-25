
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.DetalleVenta;
import model.Producto;
import model.Venta;

@ManagedBean
@ViewScoped
public class VentaBean {
    
    private Venta venta = new Venta();
    private Producto product = new Producto();
    private int cantidad;
    private List<DetalleVenta> lista = new ArrayList();
            
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }
    
    public void agregar(){
        DetalleVenta detail = new DetalleVenta();
        
        detail.setCantidad(cantidad);
        detail.setProducto(product);
        this.lista.add(detail);
    }
    
    public void registrar(){
    
    }
}
