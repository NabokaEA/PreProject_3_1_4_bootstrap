package ru.nabokae.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokae.entity.Role;

public interface RoleRepository extends JpaRepository <Role, Long>{
}
