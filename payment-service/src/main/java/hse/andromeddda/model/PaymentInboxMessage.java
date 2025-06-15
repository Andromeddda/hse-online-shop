package hse.andromeddda.model;

import jakarta.persistence.*; // @Entity, @Table, @Id, @Column, @Enumerated

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_inbox")
@Data
@Getter @Setter @NoArgsConstructor
public class PaymentInboxMessage
{

}