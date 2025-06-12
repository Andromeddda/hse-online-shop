package hse.andromeddda.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.time.LocalDateTime;

/*
    Класс - строка таблицы с аутбокс-сообщениями (retryable-таски),
    которые надо отправить брокеру сообщений (кафке)
*/

@Entity
@Table(name = "order_outbox")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderOutboxMessage
{
    /*
        Тут должно быть уникальное ID аутбокс-сообщения ()
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /*
        Тут должно быть содержание REST-запроса в формате json
    */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderOutboxStatus status;

    /*

    Это я пока не знаю зачем

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    */
}