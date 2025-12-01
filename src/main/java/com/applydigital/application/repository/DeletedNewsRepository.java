package com.applydigital.application.repository;

import com.applydigital.domain.model.DeletedNewsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlacklistRepository extends MongoRepository<DeletedNewsEntity, String> {
}
