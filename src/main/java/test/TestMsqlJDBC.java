package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMsqlJDBC {
    
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "Keko3003;");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
            ResultSet rs = instruccion.executeQuery(sql);
            while(rs.next()){
                System.out.print("Id persona: " + rs.getInt("id_persona"));
                System.out.print(" Nombre: " + rs.getString("nombre") + " ");
                System.out.print(" Apellido: " + rs.getString("apellido") + " ");
                System.out.print(" Email: " + rs.getString("email") + " ");
                System.out.print(" Teléfono: " + rs.getString("telefono") + " ");
                
                System.out.println("");
            }
            rs.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
