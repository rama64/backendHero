package security.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.entity.User;
import security.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    
    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    
    public boolean existsByUserName(String userName){
        return userRepository.existByUserName(userName);
    }
    
     public boolean existsByEmail(String email){
        return userRepository.existByEmail(email);
    }
    
    
    public void save(User user){
        userRepository.save(user);
    }
}
