package ua.hubanov.application.service;

import ua.hubanov.application.dto.SkillDTO;

import java.util.List;

public interface SkillService {

    List<SkillDTO> getAllSkills();
    SkillDTO getSkillById(Long id);
    SkillDTO save(SkillDTO newSkill);
    SkillDTO update(Long id, SkillDTO skillDetails);
    void delete(Long id);
}
