package org.onecode.firestopapi.controller;

import org.onecode.firestopapi.domain.Incident;
import org.onecode.firestopapi.service.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable String id) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident != null) {
            return new ResponseEntity<>(incident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);
        return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable String id, @RequestBody Incident incident) {
        Incident updatedIncident = incidentService.updateIncident(id, incident);
        if (updatedIncident != null) {
            return new ResponseEntity<>(updatedIncident, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable String id) {
        boolean deleted = incidentService.deleteIncident(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
