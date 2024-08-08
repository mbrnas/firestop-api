package org.onecode.firestopapi.controller;

import org.onecode.firestopapi.domain.Incident;
import org.onecode.firestopapi.service.IncidentService;
import org.onecode.firestopapi.util.ResponseUtil;
import org.onecode.firestopapi.util.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Incident>>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        ApiResponse<List<Incident>> response = ResponseUtil.success(incidents, "Incidents retrieved successfully", "/api/incidents");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Incident>> getIncidentById(@PathVariable String id) {
        Incident incident = incidentService.getIncidentById(id);
        if (incident != null) {
            ApiResponse<Incident> response = ResponseUtil.success(incident, "Incident retrieved successfully", "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Incident> response = ResponseUtil.error("Incident not found", "Incident with ID " + id + " not found", 404, "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Incident>> createIncident(@RequestBody Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);
        ApiResponse<Incident> response = ResponseUtil.success(createdIncident, "Incident created successfully", "/api/incidents");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Incident>> updateIncident(@PathVariable String id, @RequestBody Incident incident) {
        Incident updatedIncident = incidentService.updateIncident(id, incident);
        if (updatedIncident != null) {
            ApiResponse<Incident> response = ResponseUtil.success(updatedIncident, "Incident updated successfully", "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Incident> response = ResponseUtil.error("Incident not found", "Incident with ID " + id + " not found", 404, "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIncident(@PathVariable String id) {
        boolean deleted = incidentService.deleteIncident(id);
        if (deleted) {
            ApiResponse<Void> response = ResponseUtil.success(null, "Incident deleted successfully", "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            ApiResponse<Void> response = ResponseUtil.error("Incident not found", "Incident with ID " + id + " not found", 404, "/api/incidents/" + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
