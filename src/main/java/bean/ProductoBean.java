package bean;

import dao.ProductoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Producto;

@ManagedBean
@ViewScoped
public class ProductoBean {

    private Producto product = new Producto();
    private List<Producto> lstProductos;
    private String accion;

    public Producto getproduct() {
        return product;
    }

    public void setproduct(Producto product) {
        this.product = product;
    }

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
        this.limpiar();
    }

    private void registrar() throws Exception {

        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.registrar(product);
            this.listar("T");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void listar(String bandera) throws Exception {
        ProductoDAO dao;

        try {
            if (bandera.equals("F")) {
                if(!isPostBack()){
                    dao = new ProductoDAO();
                    lstProductos = dao.listar();
                }

            } else {
                dao = new ProductoDAO();
                lstProductos = dao.listar();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void leerID(Producto auxproduct) throws Exception {
        ProductoDAO dao;
        Producto tempproduct;

        try {
            dao = new ProductoDAO();
            tempproduct = dao.leerID(auxproduct);
            if (tempproduct != null) {
                this.product = tempproduct;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void modificar() throws Exception {

        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.modificar(product);
            this.listar("T");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void eliminar(Producto auxproduct) throws Exception {

        ProductoDAO dao;

        try {
            dao = new ProductoDAO();
            dao.eliminar(auxproduct);
            this.listar("T");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void operar() throws Exception {
        switch (accion) {
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }

    public void limpiar() {
        this.product.setCodigo(0);
        this.product.setNombre("");
        this.product.setPrecio(0.0);
    }
    
    private boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }
}
