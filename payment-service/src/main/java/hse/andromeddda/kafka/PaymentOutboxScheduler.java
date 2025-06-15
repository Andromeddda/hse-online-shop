package hse.andromeddda.kafka;

import hse.andromeddda.repository.PaymentOutboxRepository;
import hse.andromeddda.model.PaymentOutboxMessage;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentOutboxScheduler
{
    private final PaymentOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 10000) /* scheduled every 10 seconds */
    @Transactional
    public void processOutbox()
    {
        /* get all unprocessed messages */
        List<PaymentOutboxMessage> messages = outboxRepository.getUnprocessed();

        /* no outox messages - return */
        if (messages.isEmpty()) {
            return;
        }

        /* log */
        log.info("Found {} messages in outbox to send.", messages.size());

        /* try to send every message*/
        for (PaymentOutboxMessage message : messages)
        {
            /* schedule async task */
            var future = kafkaTemplate
                    .send(message.getTopic(), message.getMessageKey(), message.getPayload())
                    .whenComplete((result, ex) -> {
                        if (ex == null)
                            log.info("Message {} successfully outboxed to topic {}", message.getId(), message.getTopic());
                        else
                            log.error("Failed to outbox message {} to topic {}: {}", message.getId(), message.getTopic(), ex.getMessage());
                    });

            /* Await for response */
            try {
                future.get();
                message.setProcessedAt(LocalDateTime.now());
                outboxRepository.save(message);
            }
            catch (Exception e) {
                log.error("Error publishing order outbox message {}: {}", message.getId(), e.getMessage(), e);
            }
        }
    }
}