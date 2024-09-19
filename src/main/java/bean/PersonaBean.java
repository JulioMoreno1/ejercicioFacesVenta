
package bean;

import dao.PersonaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Persona;

@ManagedBean
@RequestScoped
public class PersonaBean {
    
    private Persona person = new Persona();
    private List<Persona> lstpersonas;
    
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
    
    
    
    public void registrar() throws Exception{
        
        PersonaDAO dao;
        
        try{
          dao = new PersonaDAO();
          dao.registrar(person);
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
}
