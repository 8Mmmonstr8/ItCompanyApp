package ua.hubanov.application.service;

import ua.hubanov.application.entity.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();
    Skill getSkillById(Long id);
    Skill save(Skill newSkill);
    Skill update(Long id, Skill skillDetails);
    void delete(Long id);
}
