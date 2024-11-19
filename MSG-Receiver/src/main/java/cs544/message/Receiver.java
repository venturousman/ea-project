package cs544.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@KafkaListener(topics = "book", groupId = "group3")
	public void receive(Book msg) {
		System.out.println("\n########## Received: " + msg.toString());
	}

	// @KafkaListener(topics = "book", groupId = "group3")
	// ublic void receive2(String msg) {
	// System.out.println("### Received2: " + msg);
	//

}
