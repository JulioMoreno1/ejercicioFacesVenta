package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAO {

    private Connection con;
    private Properties props = new Properties();

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection Conectar() throws IOException, SQLException, ClassNotFoundException {
        try (InputStream input = DAO.class.getClassLoader().getResourceAsStream("db.properties")) {

            if (input == null) {
                System.out.println(" Error: no se encontro el archivo de propiedades ");
                return null;
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");
            
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida");
        } catch (IOException | ClassNotFoundException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw ex;
        }
        return con;
    }

    public void Cerrar() {
        try {
            if (con != null) {
                if (con.isClosed() == false) {

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
