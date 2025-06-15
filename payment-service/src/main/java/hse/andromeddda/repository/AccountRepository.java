package hse.andromeddda.repository;

import hse.anromeddda.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Account, Long>
{
}