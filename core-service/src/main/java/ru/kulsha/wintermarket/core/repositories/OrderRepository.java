package ru.kulsha.wintermarket.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kulsha.wintermarket.core.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
