package pe.edu.idat.semana4.entity;

public class Usuario extends Persona {
    private int id;
    private String username;
    private String password;
    
    public Usuario(){
        
    }

    public Usuario(int personaId, String apellidoPaterno, String apellidoMaterno, String nombres, String fechaNacimiento, String username, String password) {
        super(personaId, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento);
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
