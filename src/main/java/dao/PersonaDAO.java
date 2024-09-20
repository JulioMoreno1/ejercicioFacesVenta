
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Persona;

public class PersonaDAO extends DAO{
    
    PreparedStatement pst;
    ResultSet rs;
    
    public void registrar(Persona person) throws Exception{
        try{
            this.Conectar();
            String insertperson = "insert into PERSONA (NOMBRE, SEXO) values(?, ?)";
            pst = this.getCon().prepareStatement(insertperson);
            pst.setString(1, person.getNombre());
            pst.setString(2, person.getSexo());
            pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
    
    public List<Persona> listar() throws Exception{
        List<Persona> lista;
        
        try{
            this.Conectar();
            String mostrarpersona = "select CODIGO, NOMBRE, SEXO from PERSONA";
            pst = this.getCon().prepareCall(mostrarpersona);
            rs = pst.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                Persona person = new Persona();
                person.setCodigo(rs.getInt("CODIGO"));
                person.setNombre(rs.getString("NOMBRE"));
                person.setSexo(rs.getString("SEXO"));
                
                lista.add(person);
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
    
    public Persona leerID(Persona auxperson) throws Exception{
        Persona person;
        try{
           this.Conectar();
           String consultarid = "select CODIGO, NOMBRE, SEXO from PERSONA where CODIGO = ?";
           pst = this.getCon().prepareStatement(consultarid);
           pst.setInt(1, auxperson.getCodigo());
           rs = pst.executeQuery();
           while(rs.next()){
               person = new Persona();
               person.setCodigo(rs.getInt("CODIGO"));
               person.setNombre(rs.getString("NOMBRE"));
               person.setSexo(rs.getString("SEXO"));
           }
        }catch(Exception ex){
            throw ex;
        }finally{
            this.Cerrar();
        }
        return auxperson;
    }
    
    public void modificar(Persona person) throws Exception{
        try{
            this.Conectar();
            String insertperson = "update PERSONA set NOMBRE = ?, SEXO = ? where CODIGO = ?";
            pst = this.getCon().prepareStatement(insertperson);
            pst.setString(1, person.getNombre());
            pst.setString(2, person.getSexo());
            pst.setInt(3, person.getCodigo());
            pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            this.Cerrar();
        }
    }
    
    public void eliminar(Persona person) throws Exception{
        try{
            this.Conectar();
            String insertperson = "delete from PERSONA where CODIGO = ?";
            pst = this.getCon().prepareStatement(insertperson);
            pst.setInt(1, person.getCodigo());
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
