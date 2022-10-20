package security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);
    
    boolean existByUserName(String userName);
    boolean existByEmail(String emal);
}
