package hse.andromeddda.repository;

import hse.andromeddda.model.PaymentInboxMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface PaymentInboxRepository extends JpaREpository<PaymentInboxMessage, Long>
{

}