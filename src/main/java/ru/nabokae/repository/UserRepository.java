package ru.nabokae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nabokae.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
    List<User> findAllByOrderByIdAsc();

    @Override
    <S extends User> S save(S entity);
}
