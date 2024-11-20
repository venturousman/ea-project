package cs544.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.dto.UserDto;
import cs544.dto.UserEvent;
import cs544.models.Role;
import cs544.models.User;
import cs544.repositories.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // @Autowired
    // private RoleRepository roleRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public User add(User user) {
        user.setPassword("$2a$10$SKww238GdhtdxsBXxbf7JeI2dSHC5IkiACM1yZtA1pwaD2puWQDN2"); // default password
        User u = userRepository.save(user);
        // send message to kafka
        var dto = new UserDto(u);
        var evt = new UserEvent("CREATE", u.getId(), dto, System.currentTimeMillis());
        kafkaProducer.sendMessage(evt);
        return u;
    }

    public User update(User user) {
        User u = userRepository.save(user);
        // send message to kafka
        var dto = new UserDto(u);
        var evt = new UserEvent("UPDATE", u.getId(), dto, System.currentTimeMillis());
        kafkaProducer.sendMessage(evt);
        return u;
    }

    public void delete(long id) {
        userRepository.deleteById(id);
        // send message to kafka
        var evt = new UserEvent("DELETE", id, null, System.currentTimeMillis());
        kafkaProducer.sendMessage(evt);
    }

    // public User addRole(long userId, long roleId) {
    // User user = userRepository.findById(userId).orElse(null);
    // if (user == null) {
    // throw new IllegalArgumentException("User not found");
    // }
    // Role role = roleRepository.findById(roleId).orElse(null);
    // if (role == null) {
    // throw new IllegalArgumentException("Role not found");
    // }
    // user.addRole(role);
    // User updatedUser = userRepository.save(user);
    // return updatedUser;
    // }

    public User addRole(User user, Role role) {
        user.addRole(role);
        User updatedUser = userRepository.save(user);
        // send message to kafka
        var dto = new UserDto(updatedUser);
        var evt = new UserEvent("UPDATE", updatedUser.getId(), dto, System.currentTimeMillis());
        kafkaProducer.sendMessage(evt);
        return updatedUser;
    }

}
