
package security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import security.enums.NameRol;


@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private NameRol nameRol;

   public Rol() {}

    public Rol(NameRol nameRol) {
        this.nameRol = nameRol;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NameRol getNameRol() {
        return nameRol;
    }

    public void setNameRol(NameRol nameRol) {
        this.nameRol = nameRol;
    }
    
    
}
