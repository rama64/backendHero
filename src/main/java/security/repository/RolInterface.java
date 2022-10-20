package security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entity.Rol;
import security.enums.NameRol;

@Repository
public interface RolInterface extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolName(NameRol nameRol);
}
