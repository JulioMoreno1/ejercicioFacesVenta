
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import model.DetalleVenta;
import model.Venta;



public class VentaDAO extends DAO{
    
    PreparedStatement pst;
    PreparedStatement auxpst;
    PreparedStatement tmppst;
    ResultSet rs;
    
    public void registrar(Venta sale, List<DetalleVenta> lstsale) throws Exception{
        try{
            this.Conectar();
            this.getCon().setAutoCommit(false);
            
            String insertsale = "insert into VENTA (FECHA, CODPERSONA, MONTO) values(?, ?, ?)";
            pst = this.getCon().prepareStatement(insertsale);
            pst.setDate(1, sale.getFecha());
            pst.setInt(2, sale.getPersona().getCodigo());
            pst.setDouble(3, sale.getMonto());
            pst.executeUpdate();
            pst.close();
            
            int codventa = -1;
            
            String selectlastsale = "select last_insert_id() from VENTA limit 1";
            tmppst = this.getCon().prepareStatement(selectlastsale);
            rs = tmppst.executeQuery();
            while (rs.next()){
               codventa = rs.getInt("1");
            }
            rs.close();
            
            for(DetalleVenta detail : lstsale){
            String insertdetail = "insert into DETALLEVENTA (CODVENTA, CODPRODUCTO, CANTIDAD) values(?, ?, ?)";
            auxpst = this.getCon().prepareStatement(insertdetail);
            auxpst.setInt(1, codventa);
            auxpst.setInt(2, detail.getProducto().getCodigo());
            auxpst.setInt(3, detail.getCantidad());
            auxpst.executeUpdate();
            auxpst.close();
            }
            this.getCon().commit();
        }
        catch(Exception ex){
            this.getCon().rollback();
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
}
