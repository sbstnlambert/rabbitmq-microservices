package be.technifutur.user.controller;

import be.technifutur.user.model.dto.UserDTO;
import be.technifutur.user.model.form.LoginForm;
import be.technifutur.user.service.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    // Return Token
    @PostMapping
    public String login(@RequestBody LoginForm form){
        return service.login(form);
    }


    // Verify the Token is valid and return unsensitive info from user
    @PreAuthorize("isAuthenticated()")
    @GetMapping(headers = "Authorization")
    public UserDTO validate(Authentication auth){
        return new UserDTO(
                (String)auth.getPrincipal(),
                auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );
    }

}
