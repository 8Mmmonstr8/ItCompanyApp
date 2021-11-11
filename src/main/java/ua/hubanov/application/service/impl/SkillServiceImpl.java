package ua.hubanov.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.application.dto.SkillDTO;
import ua.hubanov.application.entity.Skill;
import ua.hubanov.application.exception.SkillException;
import ua.hubanov.application.repository.SkillRepository;
import ua.hubanov.application.service.SkillService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillException("No skill with id " + id));
        return modelMapper.map(skill, SkillDTO.class);
    }

    @Override
    public SkillDTO save(SkillDTO newSkill) {
        Skill skill = skillRepository.save(modelMapper.map(newSkill, Skill.class));
        return modelMapper.map(skill, SkillDTO.class);
    }

    @Override
    public SkillDTO update(Long id, SkillDTO skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new SkillException("No skill with id " + id));
        skill.setName(skillDetails.getName());
        Skill updatedSkill = skillRepository.save(skill);
        return modelMapper.map(updatedSkill, SkillDTO.class);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
