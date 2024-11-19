package cs544.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
