package org.onecode.firestopapi.service;

import org.onecode.firestopapi.domain.Incident;
import org.onecode.firestopapi.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(String id) {
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(String id, Incident incident) {
        Incident existingIncident = incidentRepository.findById(id).orElse(null);
        if (existingIncident == null) {
            return null;
        }
        existingIncident.setLocation(incident.getLocation());
        existingIncident.setSeverity(incident.getSeverity());
        existingIncident.setDescription(incident.getDescription());
        existingIncident.setReporterName(incident.getReporterName());
        existingIncident.setReporterContact(incident.getReporterContact());
        existingIncident.setLatitude(incident.getLatitude());
        existingIncident.setLongitude(incident.getLongitude());
        return incidentRepository.save(existingIncident);
    }

    public boolean deleteIncident(String id) {
        Incident existingIncident = incidentRepository.findById(id).orElse(null);
        if (existingIncident == null) {
            return false;
        }
        incidentRepository.delete(existingIncident);
        return true;
    }
}
