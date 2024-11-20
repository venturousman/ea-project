package cs544.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cs544.models.User;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    // private String password;
    private String firstname;
    private String lastname;

    private List<RoleDto> roles = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        // this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        user.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // methods
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", firstname='" + firstname
                + "', lastname='" + lastname
                + "', username='" + username
                + "', roles=" + roles
                + "}";
    }
}
