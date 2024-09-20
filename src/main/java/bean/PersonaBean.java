
package bean;

import dao.PersonaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import model.Persona;

@ManagedBean
@ViewScoped
public class PersonaBean {
    
    private Persona person = new Persona();
    private List<Persona> lstpersonas;
    private String accion;
    
    public Persona getPerson() {
        return person;
    }

    public void setPerson(Persona person) {
        this.person = person;
    }

    public List<Persona> getLstpersonas() {
        return lstpersonas;
    }

    public void setLstpersonas(List<Persona> lstpersonas) {
        this.lstpersonas = lstpersonas;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
        this.limpiar();
    }
    
    private void registrar() throws Exception{
        
        PersonaDAO dao;
        
        try{
          dao = new PersonaDAO();
          dao.registrar(person);
          this.listar();
        }catch(Exception ex){
        throw ex;
        }
    }
    
    public void listar() throws Exception{
        PersonaDAO dao;
        
        try{
            dao = new PersonaDAO();
            lstpersonas = dao.listar();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    
    public void leerID(Persona auxperson) throws Exception{
        PersonaDAO dao;
        Persona tempperson;
        
        try{
            dao = new PersonaDAO();
            tempperson = dao.leerID(auxperson);
            if(tempperson != null){
                this.person = tempperson;
                this.accion = "Modificar";
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }
    
    private void modificar() throws Exception{
        
        PersonaDAO dao;
        
        try{
          dao = new PersonaDAO();
          dao.modificar(person);
          this.listar();
        }catch(Exception ex){
        throw ex;
        }
    }
    
    public void eliminar(Persona auxperson) throws Exception{
        
        PersonaDAO dao;
        
        try{
          dao = new PersonaDAO();
          dao.eliminar(auxperson);
          this.listar();
        }catch(Exception ex){
        throw ex;
        }
    }
    
    public void operar() throws Exception{
        switch(accion){
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
    
    public void limpiar(){
        this.person.setCodigo(0);
        this.person.setNombre("");
        this.person.setSexo("");
    }
}
