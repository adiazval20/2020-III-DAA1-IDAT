package pe.edu.idat.semana4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.edu.idat.semana4.entity.Usuario;

public class UsuarioDao {

    private Connection cnx;
    private static UsuarioDao instance;

    private UsuarioDao() {
        cnx = DbConnection.getCnx();
    }

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }
        return instance;
    }

    public List<Usuario> list() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT u.id, u.username, u.password, u.persona_id, p.apellido_paterno, "
                + "p.apellido_materno, p.nombres, p.fecha_nacimiento "
                + "FROM usuario u JOIN persona p ON u.persona_id = p.id";

        PreparedStatement stm = cnx.prepareStatement(query);
        ResultSet rs = stm.executeQuery();

        Usuario usuario;
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setUsername(rs.getString("username"));
            usuario.setPassword(rs.getString("password"));
            usuario.setPersonaId(rs.getInt("persona_id"));
            usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
            usuario.setApellidoMaterno(rs.getString("apellido_materno"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public Usuario save(Usuario usuario) throws SQLException {
        PreparedStatement stm;
        ResultSet gk;

        if (usuario.getPersonaId() == 0) {
            stm = cnx.prepareStatement(
                    "INSERT INTO Persona (apellido_paterno, apellido_materno, nombres, fecha_nacimiento)"
                    + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, usuario.getApellidoPaterno());
            stm.setString(2, usuario.getApellidoMaterno());
            stm.setString(3, usuario.getNombres());
            stm.setString(4, usuario.getFechaNacimiento());
            stm.execute();
            
            gk = stm.getGeneratedKeys();
            while (gk.next()) {
                usuario.setPersonaId(gk.getInt(1));
            }
        } else {
            stm = cnx.prepareStatement("UPDATE Persona SET apellido_paterno = ?, apellido_materno = ?, nombres = ?, fecha_nacimiento = ? WHERE id = ?");
            stm.setString(1, usuario.getApellidoPaterno());
            stm.setString(2, usuario.getApellidoMaterno());
            stm.setString(3, usuario.getNombres());
            stm.setString(4, usuario.getFechaNacimiento());
            stm.setInt(5, usuario.getPersonaId());
            
            stm.execute();
        }

        if (usuario.getId() == 0) {
            stm = cnx.prepareStatement("INSERT INTO Usuario (username, password, persona_id)"
                    + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, usuario.getUsername());
            stm.setString(2, usuario.getPassword());
            stm.setInt(3, usuario.getPersonaId());
            stm.execute();
            
            gk = stm.getGeneratedKeys();
            while (gk.next()) {
                usuario.setId(gk.getInt(1));
            }
        } else {
            stm = cnx.prepareStatement("UPDATE Usuario SET username = ?, password = ? WHERE id = ?");
            stm.setString(1, usuario.getUsername());
            stm.setString(2, usuario.getPassword());
            stm.setInt(3, usuario.getId());
            stm.execute();
        }
        return usuario;
    }

    public Usuario find(int id) throws SQLException {
        Usuario usuario = new Usuario();

        PreparedStatement stm = cnx.prepareStatement("SELECT * FROM Usuario u JOIN Persona p ON u.persona_id = p.id WHERE u.id = ?");
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        while(rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setUsername(rs.getString("username"));
            usuario.setPassword(rs.getString("password"));
            usuario.setPersonaId(rs.getInt("persona_id"));
            usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
            usuario.setApellidoMaterno(rs.getString("apellido_materno"));
            usuario.setNombres(rs.getString("nombres"));
            usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
        }
        
        return usuario;
    }
    
    public boolean delete(Usuario usuario) throws SQLException {
        PreparedStatement stm = cnx.prepareStatement("DELETE FROM Usuario WHERE id = ?");
        stm.setInt(1, usuario.getId());
        stm.execute();
        
        stm = cnx.prepareStatement("DELETE FROM Persona WHERE id = ?");
        stm.setInt(1, usuario.getPersonaId());
        
        return true;
    }
}
