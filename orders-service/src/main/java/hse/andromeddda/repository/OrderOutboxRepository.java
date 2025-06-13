package hse.andromeddda.repository;

import hse.andromeddda.model.OrderOutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxMessage, Long>
{
    @Query("SELECT * FROM order_outbox WHERE processed_at IS NULL ORDER BY id ASC")
    List<OrderOutboxMessage> findUnprocessed();
}