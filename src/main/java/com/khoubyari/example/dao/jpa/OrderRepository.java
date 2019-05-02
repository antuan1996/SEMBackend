package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
