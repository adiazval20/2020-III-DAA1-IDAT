package edu.idat.appdemo.service;

import edu.idat.appdemo.entity.Usuario;
import edu.idat.appdemo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    
    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }
    
    public boolean login(String username, String password) {
        Usuario usuario = repo.login(username, password);
        return usuario != null;
    }
}
