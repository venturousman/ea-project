package cs544.dto;

import java.io.Serializable;

import cs544.models.Role;

public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleDto {id=" + id + ", name=" + name + "}";
    }
}
