package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Paquete {
    int dx;
    int dy;
    int id;
    int idEmpleado;
    
    public Paquete(){
        
    }
    
    public Paquete(int x, int y, int id,int idEm){
        this.dx = x;
        this.dy = y;
        this.id = id;
        this.idEmpleado = idEm;
    }

    public int getDx() {
        return dx;
    }


    
    public void nuevoPa(int id, int dx, int dy,int idE){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.addBatch("INSERT INTO paquetes (id, dx, dy, id_empleado)"+"VALUES ( "+id+","+dx+","+dy+","+idE+")");
            sts.executeBatch();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void eliminaPa(int id){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            String eliminar = "DELETE FROM paquetes WHERE ID = "+id+"";
            sts.executeUpdate(eliminar);
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void consultarPa(){
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * from paquetes");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                System.out.println("ID paquete: " + rs.getInt("ID") + "\tCoordenada en x: " + rs.getInt("DX") + "\tCoordenada en y: " + rs.getInt("DY")+"\tID empleado:"+ rs.getInt("ID_EMPLEADO"));
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
//    public void buscaPa(int id){
//        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
//            Statement sts = conn.createStatement();
//            sts.execute("SELECT  * FROM paquetes WHERE ID = "+id+"");
//            ResultSet rs = sts.getResultSet();
//            System.out.println("ID paquete: " + rs.getInt("ID") + "\tCoordenada en x: " + rs.getInt("DX") + "\tCoordenada en y: " + rs.getInt("DY")+"\tID empleado:"+ rs.getInt("ID_EMPLEADO"));
//            conn.close();
//        }catch(SQLException ex){
//            System.out.println(ex.getNextException());
//        }        
//    }
    
    public void modificaPa(int id, int dx, int dy,int idE){
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.executeUpdate("Update paquetes set dx = "+dx+", "+"dy = "+dy+", "+"id_empleado = "+idE+"where id = "+id);
            conn.close();
        } catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void bajaBase(ArrayList<Paquete> listaPaquetes){
        
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/upslp1;create=true;user=root;password=1234")){
            Statement sts = conn.createStatement();
            sts.execute("Select * from paquetes");
            ResultSet rs = sts.getResultSet();
            listaPaquetes.removeAll(listaPaquetes);
            while(rs.next()){
                Paquete aux = new Paquete();
                aux.setId(rs.getInt("id"));
                aux.setDx(rs.getInt("dx"));
                aux.setDy(rs.getInt("dy"));
                aux.setIdEmpleado(rs.getInt("id_empleado"));
                //System.out.println("ID empleado: " + rs.getInt("id") + "\tNombre: " + rs.getString("nombre") + "\tDirección x: " + rs.getInt("dx") + "\tDirección y: " + rs.getInt("dy") +"\tTelefono: " + rs.getString("telefono") + "\tCorreo: " + rs.getString("correo"));
                listaPaquetes.add(aux);
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void subeBase(ArrayList<Paquete> listaPaquetes){
        for(Paquete paq : listaPaquetes){
            paq.eliminaPa(paq.id);
        }
        for(Paquete paq: listaPaquetes){
            paq.nuevoPa(paq.id, paq.dx, paq.dy, paq.id);
        }
    }
    
    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dirtY) {
        this.dy = dirtY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}