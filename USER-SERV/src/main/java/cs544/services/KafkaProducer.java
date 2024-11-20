package cs544.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cs544.dto.UserDto;

@Service
public class KafkaProducer {

    // private static final String TOPIC = "user_topic";
    @Value("${app.kafka.topic.user}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;

    public void sendMessage(UserDto user) {
        kafkaTemplate.send(TOPIC, user);
        System.out.println("### Sending to " + TOPIC + " a message: " + user.toString());
    }
}
