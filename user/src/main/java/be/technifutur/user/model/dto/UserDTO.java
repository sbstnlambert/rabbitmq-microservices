package be.technifutur.user.model.dto;


import java.util.List;

public class UserDTO{

    private String username;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
