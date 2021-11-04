package ua.hubanov.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.hubanov.application.entity.persons.Developer;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    @Query(value = "SELECT d.* FROM person AS d " +
    "JOIN person_skill AS ds ON d.id = ds.person_id " +
    "JOIN project_skill AS ps ON ps.skill_id = ds.skill_id " +
    "JOIN project AS p ON ps.project_id = p.id " +
    "WHERE p.id = :projectId AND ds.level >= ps.level", nativeQuery = true)
    List<Developer> getSuitableDevelopersForProject(@Param("projectId") Long projectId);
}
