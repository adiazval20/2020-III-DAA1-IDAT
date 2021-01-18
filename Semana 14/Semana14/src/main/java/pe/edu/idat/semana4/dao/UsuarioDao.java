package pe.edu.idat.semana4.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.idat.semana4.entity.Persona;
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

    public List<Usuario> listSp() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        //CTRL + SHIFT + C
//        String query = "SELECT u.id, u.username, u.password, u.persona_id, p.apellido_paterno, "
//                + "p.apellido_materno, p.nombres, p.fecha_nacimiento "
//                + "FROM usuario u JOIN persona p ON u.persona_id = p.id";
//
//        PreparedStatement stm = cnx.prepareStatement(query);
        PreparedStatement stm = cnx.prepareCall("{CALL usuario_listar(?,?,?,?,?)}");
        stm.setString(1, "UP");
        stm.setInt(2, 0);
        stm.setString(3, "");
        stm.setString(4, "");
        stm.setInt(5, 0);

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

    public Usuario save(Usuario usuario) throws Exception {
        String tipoOperacion;

        try {
            cnx.setAutoCommit(false);
            
            tipoOperacion = (usuario.getPersonaId() == 0 ? "I" : "U");
            personaIUD(usuario, tipoOperacion);
            
            tipoOperacion = (usuario.getId() == 0 ? "I" : "U");
            usuarioIUD(usuario, tipoOperacion);

            cnx.commit();
        } catch (Exception ex) {
            cnx.rollback();
            throw ex;
        } finally {
            cnx.setAutoCommit(true);
        }

        return usuario;
    }

    public Usuario find(int id) throws SQLException {
        Usuario usuario = new Usuario();

        PreparedStatement stm = cnx.prepareStatement("SELECT * FROM Usuario u JOIN Persona p ON u.persona_id = p.id WHERE u.id = ?");
        stm.setInt(1, id);

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
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
        PreparedStatement stm;

        usuarioIUD(usuario, "D");

        stm = cnx.prepareStatement("DELETE FROM Persona WHERE id = ?");
        stm.setInt(1, usuario.getPersonaId());

        return true;
    }

    private boolean usuarioIUD(Usuario usuario, String tipoOperacion) throws SQLException {
        CallableStatement cStm = cnx.prepareCall("{CALL usuario_IUD (?,?,?,?,?,?,?,?)}");

        cStm.setString(1, tipoOperacion);
        cStm.setInt(2, usuario.getId());
        cStm.setString(3, usuario.getUsername());
        cStm.setString(4, usuario.getPassword());
        cStm.setInt(5, usuario.getPersonaId());

        cStm.registerOutParameter(6, Types.INTEGER);
        cStm.registerOutParameter(7, Types.VARCHAR);
        cStm.registerOutParameter(8, Types.INTEGER);

        boolean resultado = cStm.execute();
        usuario.setId(cStm.getInt(8));

        return resultado;
    }

    private boolean personaIUD(Persona persona, String tipoOperacion) throws SQLException {
        CallableStatement stm = cnx.prepareCall("{CALL persona_IUD (?,?,?,?,?,?,?,?,?)}");
        stm.setString(1, tipoOperacion);
        stm.setInt(2, persona.getPersonaId());
        stm.setString(3, persona.getApellidoPaterno());
        stm.setString(4, persona.getApellidoMaterno());
        stm.setString(5, persona.getNombres());
        stm.setString(6, persona.getFechaNacimiento());
        
        stm.registerOutParameter(7, Types.INTEGER);
        stm.registerOutParameter(8, Types.VARCHAR);
        stm.registerOutParameter(9, Types.INTEGER);
        
        boolean resultado = stm.execute();
        persona.setPersonaId(stm.getInt(9));
        
        return resultado;
    }
}
