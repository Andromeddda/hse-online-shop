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
    class Order - row of "orders" table.
    Fields:
        id, userId, amount, status, description, createdAt, updatedAt
*/

@Entity
@Table(name = "orders")
@Data
@Getter @Setter @NoArgsConstructor
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private OrderStatus status;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*
        Constructor
    */
    public Order(Long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }
}