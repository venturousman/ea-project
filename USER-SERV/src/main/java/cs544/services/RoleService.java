package cs544.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.models.Role;
import cs544.repositories.RoleRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElse(null);
    }

    public Role add(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public void delete(long id) {
        roleRepository.deleteById(id);
    }

}
