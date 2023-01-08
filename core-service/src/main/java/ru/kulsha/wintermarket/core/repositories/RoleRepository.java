package ru.kulsha.wintermarket.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulsha.wintermarket.core.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
