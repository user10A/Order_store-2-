package store.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.orderservice.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
