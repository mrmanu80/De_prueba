package H2;
// Generated 28-nov-2011 22:36:46 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Tipo generated by hbm2java
 */
public class Tipo  implements java.io.Serializable {


     private byte idTipoUsuario;
     private String tipoUsuario;
     private Set usuarios = new HashSet(0);

    public Tipo() {
    }

	
    public Tipo(byte idTipoUsuario, String tipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.tipoUsuario = tipoUsuario;
    }
    public Tipo(byte idTipoUsuario, String tipoUsuario, Set usuarios) {
       this.idTipoUsuario = idTipoUsuario;
       this.tipoUsuario = tipoUsuario;
       this.usuarios = usuarios;
    }
   
    public byte getIdTipoUsuario() {
        return this.idTipoUsuario;
    }
    
    public void setIdTipoUsuario(byte idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    public String getTipoUsuario() {
        return this.tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}

