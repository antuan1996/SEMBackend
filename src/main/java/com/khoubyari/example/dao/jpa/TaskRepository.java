package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
