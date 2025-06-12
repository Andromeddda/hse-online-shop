package hse.andromeddda.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hse.andromeddda.model.*;       /* Order, OrderStatus, OrderOutboxMessage*/
import hse.andromeddda.dto.*;         /* CreateOrderRequest, OrderResponse, PaymentRequest, PaymentOutcome */
import hse.andromeddda.repository.*;  /* OrderRepository, OrderOutboxRepository */

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersService
{
    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @SneakyThrows
    public Order createOrder(CreateOrderRequest request)
    {
        /* Create new order */
        Order order = new Order(request.userId(), request.amount());

        /* Save order to Database*/
        orderRepository.save(order);

        /* Create message for Kafka */
        String messageId = UUID.randomUUID().toString();
        PaymentRequest paymentRequest =
            new PaymentRequest(messageId, order.getId(), order.getUserId(), order.getAmount());

        String payload = objectMapper.writeValueAsString(paymentRequest);

        /* Save message to Outbox Database */
        OrderOutboxMessage outboxMessage = new OrderOutboxMessage(payload, String.valueOf(order.getId()));
        orderOutboxRepository.save(outboxMessage);

        return order;
    }
}