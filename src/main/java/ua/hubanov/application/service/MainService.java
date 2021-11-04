package ua.hubanov.application.service;

import ua.hubanov.application.entity.persons.Developer;

import java.util.List;

public interface MainService {

    List<Developer> getSuitableDevelopersForProject(Long projectId);
}
