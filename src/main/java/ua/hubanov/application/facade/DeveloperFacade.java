package ua.hubanov.application.facade;

import ua.hubanov.application.dto.DeveloperDTO;

import java.util.List;

public interface DeveloperFacade {

    List<DeveloperDTO> getAllDevelopers();
    DeveloperDTO getDeveloperById(Long id);
    DeveloperDTO save(DeveloperDTO newDeveloper);
    DeveloperDTO update(Long id, DeveloperDTO developerDetails);
    void delete(Long id);

    DeveloperDTO addSkillToDeveloper(Long developerId, Long skillId, int level);
    List<DeveloperDTO> getSuitableDevelopersForProject(Long projectId);
}
