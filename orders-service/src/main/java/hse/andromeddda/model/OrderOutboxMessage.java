package hse.andromeddda.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/*
    class OrderOutboxMessage - row of table "order_outbox"
    Fields:
        id, payload, topic,  messageKey
*/
@Entity
@Table(name = "order_outbox")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderOutboxMessage
{
    /* id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* jsoned REST-request  */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Column(nullable = false)
    private String messageKey;

    // TODO: decide if add topic or not

    /*
        Constructor
    */
    public OrderOutboxMessage(String payload, String key)
    {
        this.messageKey = key;
        this.payload = payload;
    }
}