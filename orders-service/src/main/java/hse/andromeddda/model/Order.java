package hse.andromeddda.model;

import jakarta.persistence.*; // @Entity, @Table, @Id, @Column, @Enumerated

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

/*
     Класс - строка таблицы orders
*/

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column
    private String description;

    public Order(Long userId, Long amount, OrderStatus orderStatus)
    {
        this.userId = userId;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }
}