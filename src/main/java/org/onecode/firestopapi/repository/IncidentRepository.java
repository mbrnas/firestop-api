package org.onecode.firestopapi.repository;


import org.onecode.firestopapi.domain.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncidentRepository extends MongoRepository<Incident, String> {
}
