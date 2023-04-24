package ru.hse.amaltheateam.users.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hse.amaltheateam.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
