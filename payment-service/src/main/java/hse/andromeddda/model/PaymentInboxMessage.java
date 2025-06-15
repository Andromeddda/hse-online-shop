package hse.andromeddda.model;

import jakarta.persistence.*; /* @Entity, @Table, @Id, @Column, @Enumerated */

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
    class PaymentInboxMessage - row of "payment_inbox" table.
    Fields:
        id, processedAt
*/
@Entity
@Table(name = "payment_inbox")
@Data
@Getter @Setter @NoArgsConstructor
public class PaymentInboxMessage
{
    /* Kafka id*/
    @Id
    private String messageId;

    /*  process if null */
    @Column(nullable = false)
    private LocalDateTime processedAt;

    public PaymentInboxMessage(String messageId)
    {
        this.messageId = messageId;
        this.processedAt = null;
    }
}