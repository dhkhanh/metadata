package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.FieldDefinitionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface FieldDefinitionJpaRepository extends JpaRepository<FieldDefinitionJpaEntity, UUID> {
    
    // Tìm danh sách các trường của một entity và sắp xếp theo displayOrder
    List<FieldDefinitionJpaEntity> findByEntityCodeOrderByDisplayOrderAsc(String entityCode);
    
    // Tìm các trường hiển thị (visible = true) của một entity
    List<FieldDefinitionJpaEntity> findByEntityCodeAndVisibleTrueOrderByDisplayOrderAsc(String entityCode);
}