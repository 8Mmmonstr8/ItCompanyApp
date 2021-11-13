package ua.hubanov.application.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.hubanov.application.dto.DeveloperDTO;
import ua.hubanov.application.entity.persons.Developer;
import ua.hubanov.application.facade.DeveloperFacade;
import ua.hubanov.application.service.DeveloperService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeveloperFacadeImpl implements DeveloperFacade {

    @Autowired
    DeveloperService developerService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DeveloperDTO> getAllDevelopers() {
        return developerService.getAllDevelopers().stream()
                .map(developer -> modelMapper.map(developer, DeveloperDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeveloperDTO getDeveloperById(Long id) {
        return modelMapper.map(developerService.getDeveloperById(id), DeveloperDTO.class);
    }

    @Override
    public DeveloperDTO save(DeveloperDTO newDeveloper) {
        Developer developer = modelMapper.map(newDeveloper, Developer.class);
        return modelMapper.map(developerService.save(developer), DeveloperDTO.class);
    }

    @Override
    public DeveloperDTO update(Long id, DeveloperDTO developerDetails) {
        Developer developer = modelMapper.map(developerDetails, Developer.class);
        return modelMapper.map(developerService.update(id, developer), DeveloperDTO.class);
    }

    @Override
    public void delete(Long id) {
        developerService.delete(id);
    }

    @Override
    public DeveloperDTO addSkillToDeveloper(Long developerId, Long skillId, int level) {
        return modelMapper.map(developerService.addSkillToDeveloper(developerId, skillId, level), DeveloperDTO.class);
    }

    @Override
    public List<DeveloperDTO> getSuitableDevelopersForProject(Long projectId) {
        return developerService.getSuitableDevelopersForProject(projectId).stream()
                .map(developer -> modelMapper.map(developer, DeveloperDTO.class))
                .collect(Collectors.toList());
    }
}
