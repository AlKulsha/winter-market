package ru.kulsha.wintermarket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kulsha.wintermarket.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
