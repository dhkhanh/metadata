package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.CustomFieldJpaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomFieldJpaRepository extends JpaRepository<CustomFieldJpaEntity, UUID> {
    
    // Lấy danh sách custom field cho một entity theo thứ tự hiển thị
    List<CustomFieldJpaEntity> findByEntityCodeOrderByDisplayOrderAsc(String entityCode);
    
    // Lấy danh sách các field đang active
    List<CustomFieldJpaEntity> findByEntityCodeAndActiveTrueOrderByDisplayOrderAsc(String entityCode);
}