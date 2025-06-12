package hse.andromeddda.repository;

import hse.andromeddda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>
{
    List<Order> findByUserId(Long userId);
}