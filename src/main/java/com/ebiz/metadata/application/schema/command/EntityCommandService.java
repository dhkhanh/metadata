// EntityCommandService sẽ chịu trách nhiệm:
// - Tạo Entity Definition
// - Cập nhật Entity Definition
// - Xóa Entity Definition
// - Kích hoạt/Vô hiệu hóa Entity Definition

package com.ebiz.metadata.application.schema.command;

import com.ebiz.metadata.application.schema.dto.EntityDto;
import com.ebiz.metadata.domain.schema.model.EntityDefinition;
import com.ebiz.metadata.domain.schema.repository.EntityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EntityCommandService {

    private final EntityRepository entityRepository;

    /*** Tạo mới Entity Definition */
    public EntityDto create(
            String entityCode,
            String entityName,
            String entityClass,
            String serviceName,
            String schemaName,
            String tableName,
            String displayNameField,
            String description
    ) {

        if (entityRepository.existsByEntityCode(entityCode)) {
            throw new IllegalArgumentException(
                    "Entity code already exists: " + entityCode
            );
        }

        EntityDefinition entity = EntityDefinition.builder()
                .id(UUID.randomUUID())
                .entityCode(entityCode)
                .entityName(entityName)
                .entityClass(entityClass)
                .serviceName(serviceName)
                .schemaName(schemaName)
                .tableName(tableName)
                .primaryKeyField("id")
                .primaryKeyType("UUID")
                .displayNameField(displayNameField)
                .softDelete(true)
                .tenantEnabled(false)
                .auditable(true)
                .cacheable(false)
                .apiPath("/api/data/" + entityCode)
                .description(description)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        entityRepository.save(entity);

        return EntityDto.from(entity);
    }

    /*** Cập nhật Entity */
    public EntityDto update(
            UUID id,
            String entityName,
            String displayNameField,
            String description,
            boolean active
    ) {

        EntityDefinition entity =
                entityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Entity not found: " + id
                                )
                        );

        entity.setEntityName(entityName);
        entity.setDisplayNameField(displayNameField);
        entity.setDescription(description);
        entity.setActive(active);
        entity.setUpdatedAt(LocalDateTime.now());

        entityRepository.save(entity);

        return EntityDto.from(entity);
    }

    /*** Soft Delete */
    public void delete(UUID id) {

        EntityDefinition entity =
                entityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Entity not found: " + id
                                )
                        );

        entity.setActive(false);
        entity.setUpdatedAt(LocalDateTime.now());

        entityRepository.save(entity);
    }

    /*** Kích hoạt lại */
    public void activate(UUID id) {

        EntityDefinition entity =
                entityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Entity not found: " + id
                                )
                        );

        entity.setActive(true);
        entity.setUpdatedAt(LocalDateTime.now());

        entityRepository.save(entity);
    }
}