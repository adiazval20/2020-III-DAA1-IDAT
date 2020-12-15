package pe.edu.idat.semana4.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pe.edu.idat.semana4.entity.Usuario;

public class UsuarioRepository {

    private List<Usuario> usuarios;
    private static UsuarioRepository instance;

    private UsuarioRepository() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Perez", "Rojas", "Miguel", "01/01/2000", "jrojas", "123456"));
        usuarios.add(new Usuario(2, "Díaz", "Cabrejos", "Alejandra", "01/01/1998", "dcabrejos", "admin"));
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    public List<Usuario> list() {
        return usuarios;
    }

    public Usuario find(int id) {
        Optional<Usuario> optUsuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .reduce((u, v) -> {
                    throw new IllegalStateException("Se ha encontrado más de un elemento");
                });
        return (optUsuario.isPresent() ? optUsuario.get() : new Usuario());
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(usuarios.size() + 1);
            usuarios.add(usuario);
        } else {
            usuarios.stream()
                    .forEach(u -> {
                        if (u.getId() == usuario.getId()) {
                            u = usuario;
                        }
                    });
        }
        return usuario;
    }
}
