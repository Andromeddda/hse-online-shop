package hse.andromeddda.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    class OrderOutboxMessage - row of table "order_outbox"
    Fields:
        id, payload, topic,  messageKey
*/
@Entity
@Table(name = "order_outbox")
@Getter @Setter @NoArgsConstructor
public class OrderOutboxMessage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Body  */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    /*  Kafka key */
    @Column(nullable = false)
    private String messageKey;

    /* creation timestamp */
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    /* response timestamp (schedule if null) */
    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    /* topic for Kafka Listener */
    @Column(nullable = false, name = "topic")
    private String topic;

    /*
        Constructor
    */
    public OrderOutboxMessage(String payload, String key, String topic)
    {
        this.payload = payload;
        this.messageKey = key;
        this.createdAt = LocalDateTime.now();
        this.processedAt = null;
        this.topic = topic;
    }
}