package pe.edu.idat.semana4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        if(instance == null) {
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
        while(rs.next()) {
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
}
