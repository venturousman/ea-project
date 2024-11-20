package cs544;

import cs544.dto.UserDto;
import cs544.dto.UserEvent;
import cs544.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    private final UserService userService;

    @Autowired
    public UserConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user_topic", groupId = "user-group")
    public void consume(UserEvent userEvent) {
        if ("CREATE".equals(userEvent.getEventType())) {
            UserDto userDto = userEvent.getUserData();
            User user = new User(userDto);
            userService.createUser(user);
            System.out.println("User created: {}"+ user);
        }
        // Handle other event types as needed
        else if ("UPDATE".equals(userEvent.getEventType())) {
            UserDto userDto = userEvent.getUserData();
            User user = new User(userDto);
            userService.updateUser(user);
            System.out.println("User updated: {}"+ user);
        }
        else if ("DELETE".equals(userEvent.getEventType())) {
            Long userId = userEvent.getUserId();
            userService.deleteUser(userId);
            System.out.println("User deleted with ID: {}"+ userId);
        }
    }
}