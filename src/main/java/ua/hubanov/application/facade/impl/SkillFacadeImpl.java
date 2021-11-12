package ua.hubanov.application.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.hubanov.application.dto.SkillDTO;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.facade.SkillFacade;
import ua.hubanov.application.service.SkillService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillFacadeImpl implements SkillFacade {

    @Autowired
    private SkillService skillService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillService.getAllSkills().stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        return modelMapper.map(skillService.getSkillById(id), SkillDTO.class);
    }

    @Override
    public SkillDTO save(SkillDTO newSkill) {
        Skill skill = modelMapper.map(newSkill, Skill.class);
        return modelMapper.map(skillService.save(skill), SkillDTO.class);
    }

    @Override
    public SkillDTO update(Long id, SkillDTO skillDetails) {
        Skill skill = modelMapper.map(skillDetails, Skill.class);
        return modelMapper.map(skillService.update(id, skill), SkillDTO.class);
    }

    @Override
    public void delete(Long id) {
        skillService.delete(id);
    }
}
