package ru.nabokae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokae.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Long>{

    @Override
    List<Role> findAll();
}
