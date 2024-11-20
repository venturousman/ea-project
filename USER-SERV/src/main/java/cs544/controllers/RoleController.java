package cs544.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.models.Role;
import cs544.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> add(@RequestBody Role role) {
        var newRole = roleService.add(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable long id, @RequestBody Role role) {
        var existingRole = roleService.getRoleById(id);
        if (existingRole == null) {
            return ResponseEntity.notFound().build();
        }
        role.setId(id);
        return ResponseEntity.ok(roleService.update(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        var existingRole = roleService.getRoleById(id);
        if (existingRole == null) {
            return ResponseEntity.notFound().build();
        }
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @PostMapping("/{roleId}/users/{userId}")
    // public ResponseEntity<Void> assignUserToRole(@PathVariable long roleId,
    // @PathVariable long userId) {
    // // roleService.assignUserToRole(roleId, userId); // TODO
    // return ResponseEntity.ok().build();
    // }

}
