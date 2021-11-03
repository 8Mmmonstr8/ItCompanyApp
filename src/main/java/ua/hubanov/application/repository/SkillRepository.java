package ua.hubanov.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hubanov.application.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
