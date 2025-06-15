package hse.andromeddda.model;

import jakarta.persistence.*; // @Entity, @Table, @Id, @Column, @Enumerated

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
    class PaymentOutboxMessage - row of "payment_outbox" table.
    Fields:
        id, topic, messageKey, payload
*/
@Entity
@Table(name = "payment_outbox")
@Data
@Getter @Setter @NoArgsConstructor
public class PaymentOutboxMessage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Body */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    /* Kafka key*/
    @Column(nullable = false)
    private String messageKey;

    /* Topic for Kafka Listener */
    @Column(nullable = false)
    private String topic;

    /* Send time (schedule if null) */
    @Column
    private LocalDateTime processedAt;

    /*
        Constructor
    */
    public PaymentOutboxMessage(String topic, String key, String payload)
    {
        this.topic = topic;
        this.messageKey = key;
        this.payload = payload;
        this.processedAt = null;
    }
}