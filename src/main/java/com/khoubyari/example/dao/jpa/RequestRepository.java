package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
