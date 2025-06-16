package hse.andromeddda.repository;

import hse.andromeddda.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    Optional<Account> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}