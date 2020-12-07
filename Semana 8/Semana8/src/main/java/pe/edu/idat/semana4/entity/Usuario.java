package pe.edu.idat.semana4.entity;

public class Usuario extends Persona {

    private String username;
    private String password;
    
    public Usuario(){
        
    }

    public Usuario(int id, String apellidoPaterno, String apellidoMaterno, String nombres, String fechaNacimiento, String username, String password) {
        super(id, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
