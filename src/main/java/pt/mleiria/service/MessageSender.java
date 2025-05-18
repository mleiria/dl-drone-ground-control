package pt.mleiria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pt.mleiria.dt.DestinationQueue;
import pt.mleiria.dto.Pair;

@Service
public class MessageSender {



    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final String request, final DestinationQueue destinationQueue) {
        jmsTemplate.convertAndSend(destinationQueue.name(), request);
    }


    public void sendMessage(Pair<Integer, String> message) {
        jmsTemplate.convertAndSend("ack", message);
    }
}
