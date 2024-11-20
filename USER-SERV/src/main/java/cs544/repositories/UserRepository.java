package cs544.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
