package ua.hubanov.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.exception.SkillException;
import ua.hubanov.application.repository.SkillRepository;
import ua.hubanov.application.service.SkillService;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new SkillException("No skill with id " + id));
    }

    @Override
    public Skill save(Skill newSkill) {
        return skillRepository.save(newSkill);
    }

    @Override
    public Skill update(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillException("No skill with id " + id));
        skill.setName(skillDetails.getName());
        return skillRepository.save(skill);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
