package ua.hubanov.application.service;

import ua.hubanov.application.entity.persons.Developer;

import java.util.List;

public interface DeveloperService {

    List<Developer> getAllDevelopers();
    Developer getDeveloperById(Long id);
    Developer save(Developer newDeveloper);
    Developer update(Long id, Developer developerDetails);
    void delete(Long id);

    Developer addSkillToDeveloper(Long developerId, Long skillId, int level);
    List<Developer> getSuitableDevelopersForProject(Long projectId);
}
