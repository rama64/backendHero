package security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import security.entity.MainUser;

@Component
public class jwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(jwtProvider.class);
     
   @Value("${jwt.secret}")
    private String secret;
   @Value("${jwt.expiration}")
    private int expiration;
   
   public String generateToken(Authentication authentication){
       MainUser mainUser = (MainUser) authentication.getPrincipal();
       return Jwts.builder() 
               .setSubject(mainUser.getUsername())
               .setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime()+ expiration * 1000))
               .signWith(SignatureAlgorithm.HS512, secret) //deprecated update soon
               .compact();
   }
   
   public String getUserNameFromToken(String token){
       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
   }
   
   public boolean validateToken(String token){
       try{
           Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
           return true;
       }catch(MalformedJwtException e){
           logger.error("Malformed Token");
       }catch(UnsupportedJwtException e){
           logger.error("Unsupported token");
       }catch(ExpiredJwtException e){
           logger.error("Expired token");
       }catch(IllegalArgumentException e){
           logger.error("empty token");
       }catch(SignatureException e){
           logger.error("unvalid signature");
       }
       return false;
   }  
}
