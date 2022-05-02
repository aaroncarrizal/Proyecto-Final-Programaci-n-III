package main; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Empleado {
    public int id;
    public String nombre;
    int dx;
    int dy;
    public int telefono;
    public String correo;
    
    Empleado(){
    }
    
    Empleado(int id, String nombre, int dx,int dy, int telefono, String correo){
        this.id = id;
        this.nombre = nombre;
        this.dx = dx;
        this.dy = dy;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    public void nuevoEm(int id, String nombre, int dx, int dy, int telefono,String correo){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.addBatch("INSERT INTO empleados (id, nombre, dx, dy, telefono, correo)"+"VALUES ("+id+",'"+nombre+"',"+dx+","+dy+","+telefono+",'"+correo+"')" );
            sts.executeBatch();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void eliminaEm(int id){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            String eliminar = "DELETE FROM empleados WHERE ID = "+id+"";
            sts.executeUpdate(eliminar);
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void consultarEm(){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * from empleados");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                System.out.println("ID empleado: " + rs.getInt("id") + "\tNombre: " + rs.getString("nombre") + "\tDirección x: " + rs.getInt("dx") + "\tDirección y: " + rs.getInt("dy") +"\tTelefono: " + rs.getString("telefono") + "\tCorreo: " + rs.getString("correo"));
               
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void modificaEm(int id, String nombre, int dx, int dy, int telefono,String correo){
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.executeUpdate("Update empleados set nombre = '"+nombre+"', "+"dx = "+dx+","+"dy = "+dy+","+"telefono = "+telefono+","+"correo = '"+correo+"'where id = "+id);
            conn.close();
        } catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void bajaBase(ArrayList<Empleado> listaEmpleados){
        
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * from empleados");
            ResultSet rs = sts.getResultSet();
            listaEmpleados.removeAll(listaEmpleados);
            while(rs.next()){
                Empleado aux = new Empleado();
                aux.setId(rs.getInt("id"));
                aux.setCorreo(rs.getString("correo"));
                aux.setDx(rs.getInt("dx"));
                aux.setDy(rs.getInt("dy"));
                aux.setNombre(rs.getString("nombre"));
                aux.setTelefono(rs.getInt("telefono"));
                //System.out.println("ID empleado: " + rs.getInt("id") + "\tNombre: " + rs.getString("nombre") + "\tDirección x: " + rs.getInt("dx") + "\tDirección y: " + rs.getInt("dy") +"\tTelefono: " + rs.getString("telefono") + "\tCorreo: " + rs.getString("correo"));
                listaEmpleados.add(aux);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void subeBase(ArrayList<Empleado> listaEmpleados){
        for(Empleado emp : listaEmpleados){
            emp.eliminaEm(emp.getId());
        }
        for(Empleado emp: listaEmpleados){
            emp.nuevoEm(emp.id, emp.nombre, emp.dx, emp.dy, emp.telefono, emp.correo);
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}