package ua.hubanov.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.application.entity.persons.Developer;
import ua.hubanov.application.repository.DeveloperRepository;
import ua.hubanov.application.service.MainService;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    DeveloperRepository developerRepository;

    @Override
    public List<Developer> getSuitableDevelopersForProject(Long projectId) {
        return developerRepository.getSuitableDevelopersForProject(projectId);
    }
}
