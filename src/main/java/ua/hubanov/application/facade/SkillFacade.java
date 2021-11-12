package ua.hubanov.application.facade;

import ua.hubanov.application.dto.SkillDTO;

import java.util.List;

public interface SkillFacade {

    List<SkillDTO> getAllSkills();
    SkillDTO getSkillById(Long id);
    SkillDTO save(SkillDTO newSkill);
    SkillDTO update(Long id, SkillDTO skillDetails);
    void delete(Long id);
}
