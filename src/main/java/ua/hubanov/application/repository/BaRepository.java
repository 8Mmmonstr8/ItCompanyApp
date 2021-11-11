package ua.hubanov.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hubanov.application.entity.persons.Ba;

@Repository
public interface BaRepository extends JpaRepository<Ba, Long> {
}
