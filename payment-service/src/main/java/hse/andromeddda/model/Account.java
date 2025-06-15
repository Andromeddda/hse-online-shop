package hse.andromeddda.model;

import jakarta.persistence.*; // @Entity, @Table, @Id, @Column, @Enumerated, @Version

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
    class Account - row of "accounts" table.
    Fields:
        id, userId, userId, balance, version
*/
@Entity
@Table(name = "accounts")
@Data
@Getter @Setter @NoArgsConstructor
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false)
    private BigDecimal balance;

    @Version
    private Long version;

    /*
        Constructor
    */
    public Account(Long userId)
    {
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
    }
}
