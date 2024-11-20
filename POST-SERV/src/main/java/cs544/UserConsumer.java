package cs544;

import cs544.dto.UserEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @PostConstruct
    public void init() {
        logger.error("UserConsumer initialized and ready to consume messages from Kafka topic.");
    }

    @KafkaListener(topics = "user_topic", groupId = "user-group")
    public void consume(UserEvent userEvent) {
        System.out.println("### Consumed message: " + userEvent);
        logger.error("Consumed message: {}", userEvent);
        // Process the userEvent as needed
    }
}