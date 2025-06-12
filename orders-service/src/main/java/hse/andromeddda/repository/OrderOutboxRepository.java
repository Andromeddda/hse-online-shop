package hse.andromeddda.repository;

import hse.andromeddda.model.OrderOutboxMessage;
import hse.andromeddda.model.OrderOutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxMessage, UUID>
{
    List<OrderOutboxMessage> findByStatus(OrderOutboxStatus status);
}