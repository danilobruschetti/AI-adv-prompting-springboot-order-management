package com.example.pilotesordermanagement.repository;

import com.example.pilotesordermanagement.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query(
      "SELECT o FROM Order o JOIN o.customer c WHERE lower(c.firstName) LIKE lower(concat('%', :query, '%')) OR lower(c.lastName) LIKE lower(concat('%', :query, '%'))")
  List<Order> findByCustomerNameContainingIgnoreCase(@Param("query") String query);
}
