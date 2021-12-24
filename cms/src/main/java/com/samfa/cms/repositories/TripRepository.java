package com.samfa.cms.repositories;

import com.samfa.cms.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, String> {
	Trip findByReferenceNumber(String referenceNumber);
}
