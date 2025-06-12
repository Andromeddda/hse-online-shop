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

        /* Save order to DB*/
        orderRepository.save(order);

        /* Create message for Kafka */
        String messageId = UUID.randomUUID().toString();
        PaymentRequest paymentRequest =
            new PaymentRequest(messageId, order.getId(), order.getUserId(), order.getAmount());

        String payload = objectMapper.writeValueAsString(paymentRequest);

        /* Save message to Outbox DB */
        OrderOutboxMessage outboxMessage = new OrderOutboxMessage(payload, String.valueOf(order.getId()));
        orderOutboxRepository.save(outboxMessage);

        return order;
    }

    @Transactional
    @SneakyThrows
    public void updateOrderStatus(PaymentOutcome outcome)
    {
        /* Get order from DB by id*/
        Order order = orderRepository.findById(outcome.orderId());
            .orElseThrow(() -> new IllegalStateException("Order not found with id: " + outcome.orderId()));

        /* Define new status */
        OrderStatus newStatus = null;
        if ("SUCCESS".equals(outcome.status()))
            newStatus = OrderStatus.FINISHED;
        else
            newStatus = OrderStatus.CANCELLED;

        /* Update order data */
        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());

        /* Save updated data (idempotence operation) */
        orderRepository.save(order);
    }
}