package ua.hubanov.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hubanov.application.entity.projects.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
