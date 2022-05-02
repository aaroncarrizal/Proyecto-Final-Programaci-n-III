package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario {
    
    String nombre;
    String contra;
    boolean admin;
    
    public Usuario(){
        
    }
    
    public Usuario(String nombre, String contra, boolean admin){
        this.admin = admin;
        this.contra = contra;
        this.nombre = nombre;
   }
    
    public void nuevoUs(String nombre, String contra, boolean admin){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.addBatch("INSERT INTO usuarios (usuario, contra, admin)"+"VALUES ( '"+nombre+"','"+contra+"',"+admin+")");
            sts.executeBatch();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void eliminaUs(String nombre){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            String eliminar = "DELETE FROM usuarios WHERE USUARIO = '"+nombre+"'";
            sts.executeUpdate(eliminar);
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void modificaUs(String nombre, String contra, boolean admin){
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.executeUpdate("Update USUARIOS set contra = '"+contra+"', "+"admin = '"+admin+"'where USUARIO = '"+nombre+"'");
            conn.close();
        } catch(SQLException ex){
            System.out.println(ex.getCause());
            System.out.println(ex.getErrorCode());
        }
    }
    
    public void bajaBase(ArrayList<Usuario> listaUsuarios){
        
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * from usuarios");
            ResultSet rs = sts.getResultSet();
            listaUsuarios.removeAll(listaUsuarios);
            while(rs.next()){
                Usuario aux = new Usuario();
                aux.setNombre(rs.getString("usuario"));
                aux.setContra(rs.getString("contra"));
                aux.setAdmin(rs.getBoolean("admin"));
                listaUsuarios.add(aux);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void subeBase(ArrayList<Usuario> listaUsuarios){
        for(Usuario us : listaUsuarios){
            us.eliminaUs(us.nombre);
        }
        for(Usuario us: listaUsuarios){
            us.nuevoUs(us.nombre, us.contra, us.admin);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
