
package security.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.entity.Rol;
import security.enums.NameRol;
import security.repository.RolInterface;

@Service
@Transactional
public class RolService {
    @Autowired
    RolInterface rolRepository;
    
    public Optional<Rol> getByNameRol(NameRol nameRol){
        return rolRepository.findByRolName(nameRol);
    }
    
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
