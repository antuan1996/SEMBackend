package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
}
