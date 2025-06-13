package hse.andromeddda.repository;

import hse.andromeddda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;



import java.util.UUID;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    @Query("SELECT * FROM orders WHERE user_id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
}