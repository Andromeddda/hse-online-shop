package hse.andromeddda.repository;

import hse.andromeddda.model.PaymentOutboxMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentOutboxRepository extends JpaRepository<PaymentOutboxMessage, Long>
{
    @Query("SELECT o FROM PaymentOutboxMessage o WHERE o.processedAt IS NULL ORDER BY id ASC")
    List<PaymentOutboxMessage> getUnprocessed();
}