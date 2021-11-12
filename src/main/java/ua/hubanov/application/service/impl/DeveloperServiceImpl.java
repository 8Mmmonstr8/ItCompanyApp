package ua.hubanov.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.entity.enums.Level;
import ua.hubanov.application.entity.persons.Developer;
import ua.hubanov.application.exception.DeveloperException;
import ua.hubanov.application.exception.SkillException;
import ua.hubanov.application.repository.DeveloperRepository;
import ua.hubanov.application.repository.SkillRepository;
import ua.hubanov.application.service.DeveloperService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Developer getDeveloperById(Long id) {
       return developerRepository.findById(id)
               .orElseThrow(() -> new DeveloperException("No developer with id: " + id));
    }

    @Override
    public Developer save(Developer newDeveloper) {
        return developerRepository.save(newDeveloper);
    }

    @Override
    public Developer update(Long id, Developer developerDetails) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperException("No developer with id: " + id));
        developer.setName(developerDetails.getName());
        developer.setSurname(developerDetails.getSurname());
        developer.setSex(developerDetails.getSex());
        developer.setDateOfBirth(developerDetails.getDateOfBirth());
        return developerRepository.save(developer);
    }

    @Override
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Developer addSkillToDeveloper(Long developerId, Long skillId, int level) {
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperException("No developer with id: " + developerId));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new SkillException("No skill with id " + skillId));

        developer.getSkills().stream()
                .filter(developerSkill -> developerSkill.getSkill().equals(skill))
                .findAny()
                .ifPresent(developerSkill -> {
                    throw new SkillException(developerSkill.getSkill().getName() + " already exists for this developer");
                });

        developer.addSkill(skill, Level.of(level));
        skillRepository.save(skill);
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getSuitableDevelopersForProject(Long projectId) {
        return developerRepository.getSuitableDevelopersForProject(projectId);
    }
}
