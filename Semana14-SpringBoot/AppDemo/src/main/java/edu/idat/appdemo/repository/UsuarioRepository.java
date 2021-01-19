package edu.idat.appdemo.repository;

import edu.idat.appdemo.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
    Usuario login(@Param("username") String username, @Param("password") String password);
}
