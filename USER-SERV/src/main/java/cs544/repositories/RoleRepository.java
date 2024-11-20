package cs544.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
