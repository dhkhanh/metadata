package com.ebiz.metadata.infrastructure.datasource.repository;

import com.ebiz.metadata.infrastructure.datasource.entity.ScreenComponentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScreenComponentJpaRepository extends JpaRepository<ScreenComponentJpaEntity, UUID> {

    List<ScreenComponentJpaEntity> findByScreenIdOrderByDisplayOrderAsc(UUID screenId);

    void deleteByScreenId(UUID screenId);
    long countByScreenId(UUID screenId);
}