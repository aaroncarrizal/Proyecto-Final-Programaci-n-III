package main; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Personal {
    public int numeroEmpleado;
    public String nombre;
    public String direccion;
    public int telefono;
    public String correo;
    
    Personal(){
    }
    
    Personal(int numeroEmpleado, String nombre, String direccion, int telefono, String correo){
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    public void nuevoEm(){
        Scanner ent  = new Scanner(System.in);
        int idEm;
        String nom;
        String dir;
        String tel;
        String cor;
        System.out.println("Ingresa el id:");
        idEm = ent.nextInt();
        ent.nextLine();
        System.out.println("Ingresa el nombre:");
        nom = ent.nextLine();
        System.out.println("Ingresa la dirección:");
        dir = ent.nextLine();
        System.out.println("Ingresa el telefono:");
        tel = ent.nextLine();
        System.out.println("Ingresa el correo:");
        cor = ent.nextLine();
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ServiciMensajeria;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.addBatch("INSERT INTO empleados (id, nombre, direccion, telefono, correo)"+"VALUES ( "+idEm+",'"+nom+"','"+dir+"','"+tel+"','"+cor+"')");
            sts.executeBatch();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void eliminaEm(){
        Scanner ent  = new Scanner(System.in);
        int idEm;
        System.out.println("Ingresa el id a eliminar:");
        idEm = ent.nextInt();
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ServiciMensajeria;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            String eliminar = "DELETE FROM empleados WHERE ID = "+idEm+"";
            sts.executeUpdate(eliminar);
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void consultarEm(){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ServiciMensajeria;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * FROM empleados");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                System.out.println("ID empleado: " + rs.getInt("id") + "\tNombre: " + rs.getString("nombre") + "\tDirección: " + rs.getString("direccion") + "\tTelefono: " + rs.getString("telefono") + "\tCorreo: " + rs.getString("correo"));
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
}