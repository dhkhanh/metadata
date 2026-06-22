package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.EntityDefinitionJpaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EntityDefinitionJpaRepository extends JpaRepository<EntityDefinitionJpaEntity, UUID> {

    Optional<EntityDefinitionJpaEntity> findByEntityCode(String entityCode);

    boolean existsByEntityCode(String entityCode);
}