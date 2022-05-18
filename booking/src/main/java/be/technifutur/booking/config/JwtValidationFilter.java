package be.technifutur.booking.config;

import be.technifutur.booking.model.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;

    public JwtValidationFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if( token != null ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            try {
                ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
                        "http://user-service/login",
                        HttpMethod.GET,
                        httpEntity,
                        UserDTO.class
                );
                if (responseEntity.getStatusCode().is2xxSuccessful()) {

                    UserDTO user = responseEntity.getBody();
                    Authentication auth = new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            null,
                            user.getRoles().stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .toList()
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            catch (HttpClientErrorException ignored){}
        }
        filterChain.doFilter(request,response);
    }
}
