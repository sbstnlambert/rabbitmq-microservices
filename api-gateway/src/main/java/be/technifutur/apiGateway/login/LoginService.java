package be.technifutur.apiGateway.login;

import be.technifutur.apiGateway.jwtConfig.JwtProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    private final AuthenticationManager manager;
    private final JwtProperties properties;

    public LoginService(AuthenticationManager manager, JwtProperties properties) {
        this.manager = manager;
        this.properties = properties;
    }

    public String login(LoginForm form) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        authentication = manager.authenticate(authentication);
        return JWT.create()
                .withSubject(form.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + properties.getExpires()))
                .withClaim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(Algorithm.HMAC512(properties.getSecret()));
    }
}
