package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.FormDefinitionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface FormDefinitionJpaRepository extends JpaRepository<FormDefinitionJpaEntity, UUID> {
    
    List<FormDefinitionJpaEntity> findByEntityId(UUID entityId); //Tìm danh sách form theo entityId     
    Optional<FormDefinitionJpaEntity> findByFormCode(String formCode); //Tìm Form theo Form Code 
    boolean existsByFormCode(String formCode); //Kiểm tra tồn tại form theo Form Code
    List<FormDefinitionJpaEntity> findByNameContainingIgnoreCase(String name); //Tìm danh sách form theo tên (name) có chứa chuỗi tìm kiếm, không phân biệt chữ hoa chữ thường
}