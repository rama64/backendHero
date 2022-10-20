package security.controller;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.Dto.JwtDTO;
import security.Dto.NewUser;
import security.Dto.UserLogin;
import security.entity.Rol;
import security.entity.User;
import security.enums.NameRol;
import security.jwt.jwtProvider;
import security.service.RolService;
import security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    jwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return new ResponseEntity(new Message("invalid field"), HttpStatus.BAD_REQUEST);
        
        if(userService.existsByUserName(newUser.getUserName())) return new ResponseEntity(new Message("the user name already exists"), HttpStatus.BAD_REQUEST);
               
        if(userService.existsByEmail(newUser.getEmail())) return new ResponseEntity(new Message("the email already exists"), HttpStatus.BAD_REQUEST);
        
        User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        
        Set<Rol> rolSet = new HashSet<>();
        rolSet.add(rolService.getByNameRol(NameRol.ROLE_USER).get());
        
        if(newUser.getRoles().contains("admin")) rolSet.add(rolService.getByNameRol(NameRol.ROLE_ADMIN).get());
        user.setRols(rolSet);
        userService.save(user);
        return new ResponseEntity(new Message("User created successfully"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        if(bindingResult.hasErrors())  return new ResponseEntity(new Message("There was an error with the user or password"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        userLogin.getUserName(),
                userLogin.getPassword()
        ));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }
}
