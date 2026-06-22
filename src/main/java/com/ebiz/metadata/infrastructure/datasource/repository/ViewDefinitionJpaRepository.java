package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.ViewDefinitionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ViewDefinitionJpaRepository extends JpaRepository<ViewDefinitionJpaEntity, UUID> {
    
    // Tìm các view theo entityId
    List<ViewDefinitionJpaEntity> findByEntityId(UUID entityId);
    Optional<ViewDefinitionJpaEntity> findByViewCode(String viewCode);
}