package cs544.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.dto.UserDto;
import cs544.models.User;
import cs544.repositories.RoleRepository;
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
        var u = userRepository.save(user);
        // send message to kafka
        var dto = new UserDto(u);
        kafkaProducer.sendMessage(dto);
        return u;
    }

    public User update(User user) {
        var u = userRepository.save(user);
        // send message to kafka
        var dto = new UserDto(u);
        kafkaProducer.sendMessage(dto);
        return u;
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
