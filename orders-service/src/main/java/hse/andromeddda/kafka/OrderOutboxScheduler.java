package hse.andromeddda.kafka;

import hse.andromeddda.repository.OrderOutboxRepository;
import hse.andromeddda.model.OrderOutboxMessage;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.lang.Exception;
import java.time.LocalDateTime;

/*
    Scheduler for sending payment request
*/
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderOutboxScheduler
{
    private final OrderOutboxRepository orderOutboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /*
        Scheduled every 10 seconds
    */
    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void processOutbox()
    {
        /* get all unprocessed messages */
        List<OrderOutboxMessage> outboxMessages = orderOutboxRepository.findUnprocessed();

        /* no outbox messages - return */
        if(outboxMessages.isEmpty())
            return;

        /* log */
        log.info("Found {} messages in outbox to send.", outboxMessages.size());

        /* try to send every message */
        for (OrderOutboxMessage message : outboxMessages)
        {
            /* Schedule async task : send */
            var future = kafkaTemplate
                    .send(message.getTopic(), message.getMessageKey(), message.getPayload())
                    .whenComplete((result, ex) -> {
                        if (ex == null)
                            log.info("Message {} successfully outboxed to topic {}. Content: \"{}\"", message.getId(), message.getTopic(), message.getPayload());
                        else
                            log.error("Failed to outbox message {} to topic {}: {}", message.getId(), message.getTopic(), ex.getMessage());
                    });

            /* Await for response */
            try {
                future.get();
                message.setProcessedAt(LocalDateTime.now());
                orderOutboxRepository.save(message);
            }
            catch (Exception e) {
                log.error("Error publishing order outbox message {}: {}", message.getId(), e.getMessage(), e);
            }
        }

    }
}