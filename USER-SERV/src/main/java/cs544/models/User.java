package cs544.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Schema(description = "Details about a user")
@Entity
// public class User implements UserDetails {
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the user")
    private Long id;
    @Schema(description = "The email/username of the user")
    private String username;
    private String password;
    @Schema(description = "The firstname of the user")
    private String firstname;
    @Schema(description = "The lastname of the user")
    private String lastname;

    @ManyToMany // (cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getFullname() {
        return firstname + " " + lastname;
    }

    // methods
    public void addRole(Role role) {
        roles.add(role);
        // role.getUsers().add(this); // Synchronize both sides
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public String[] getRoleNames() {
        return roles.stream().map(Role::getName).toArray(String[]::new);
    }

    public List<Role> getRoles() {
        return roles;
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // return roles.stream()
    // // .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
    // .map(role -> new SimpleGrantedAuthority(role.getName()))
    // .collect(Collectors.toList());
    // }

}
