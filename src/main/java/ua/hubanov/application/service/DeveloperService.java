package ua.hubanov.application.service;

import ua.hubanov.application.dto.DeveloperDTO;

import java.util.List;

public interface DeveloperService {

    List<DeveloperDTO> getAllDevelopers();
    DeveloperDTO getDeveloperById(Long id);
    DeveloperDTO save(DeveloperDTO newDeveloper);
    DeveloperDTO update(Long id, DeveloperDTO developerDetails);
    void delete(Long id);

    DeveloperDTO addSkillToDeveloper(Long developerId, Long skillId, int level);
}
