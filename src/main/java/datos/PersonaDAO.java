package datos;

import static datos.Conexion.close;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?) ";
    private static final String SQL_DELETE = "DELETE FROM test.persona WHERE id_persona = ?";
    private static final String SQL_UPDATE = "UPDATE test.persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";

    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                personas.add(persona);
            }
            System.out.println("It works... READ");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(ps);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }

    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getEmail());
            ps.setString(4, persona.getTelefono());
            registros = ps.executeUpdate();
            System.out.println("It works... CREATE");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, persona.getIdPersona());
            registros = ps.executeUpdate();
            System.out.println("It works... DELETE");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int actualizar(Persona persona){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setString(3, persona.getEmail());
            ps.setString(4, persona.getTelefono());
            ps.setInt(5, persona.getIdPersona());
            registros = ps.executeUpdate();
            System.out.println("It works... UPDATE");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
