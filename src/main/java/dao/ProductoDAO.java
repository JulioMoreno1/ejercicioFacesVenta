
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Producto;

public class ProductoDAO extends DAO{
    
    PreparedStatement pst;
    ResultSet rs;
    
    public void registrar(Producto product) throws Exception{
        try{
            this.Conectar();
            String insertproduct = "insert into PRODUCTO (NOMBRE, PRECIO) values(?, ?)";
            pst = this.getCon().prepareStatement(insertproduct);
            pst.setString(1, product.getNombre());
            pst.setDouble(2, product.getPrecio());
            pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
    
    public List<Producto> listar() throws Exception{
        List<Producto> lista;
        
        try{
            this.Conectar();
            String mostrarproducto = "select CODIGO, NOMBRE, PRECIO from PRODUCTO";
            pst = this.getCon().prepareCall(mostrarproducto);
            rs = pst.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                Producto product = new Producto();
                product.setCodigo(rs.getInt("CODIGO"));
                product.setNombre(rs.getString("NOMBRE"));
                product.setPrecio(rs.getDouble("PRECIO"));
                
                lista.add(product);
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
        return lista;
    }
    
    public Producto leerID(Producto auxproduct) throws Exception{
        Producto product;
        try{
           this.Conectar();
           String consultarid = "select CODIGO, NOMBRE, PRECIO from PRODUCTO where CODIGO = ?";
           pst = this.getCon().prepareStatement(consultarid);
           pst.setInt(1, auxproduct.getCodigo());
           rs = pst.executeQuery();
           while(rs.next()){
               product = new Producto();
               product.setCodigo(rs.getInt("CODIGO"));
               product.setNombre(rs.getString("NOMBRE"));
               product.setPrecio(rs.getDouble("PRECIO"));
           }
        }catch(Exception ex){
            throw ex;
        }finally{
            this.Cerrar();
        }
        return auxproduct;
    }
    
    public void modificar(Producto product) throws Exception{
        try{
            this.Conectar();
            String insertproduct = "update PRODUCTO set NOMBRE = ?, PRECIO = ? where CODIGO = ?";
            pst = this.getCon().prepareStatement(insertproduct);
            pst.setString(1, product.getNombre());
            pst.setDouble(2, product.getPrecio());
            pst.setInt(3, product.getCodigo());
            pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
    
    public void eliminar(Producto product) throws Exception{
        try{
            this.Conectar();
            String deletproduct = "delete from PRODUCTO where CODIGO = ?";
            pst = this.getCon().prepareStatement(deletproduct);
            pst.setInt(1, product.getCodigo());
            pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
    
}
