// infrastructure/persistence/entity/EntityDefinitionJpaEntity.java
package com.ebiz.metadata.infrastructure.persistence.entity;

import com.ebiz.metadata.infrastructure.persistence.converter.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "entity_definition")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDefinitionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "entity_code", unique = true, nullable = false, length = 100)
    private String entityCode;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "entity_class", nullable = false, length = 500)
    private String entityClass;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "schema_name", length = 100)
    private String schemaName;

    @Column(name = "table_name", nullable = false, length = 100)
    private String tableName;

    @Column(name = "primary_key_field", nullable = false, length = 100)
    private String primaryKeyField;

    @Column(name = "primary_key_type", nullable = false, length = 50)
    private String primaryKeyType;

    @Column(name = "display_name_field", length = 100)
    private String displayNameField;

    @Column(name = "soft_delete")
    private boolean softDelete;

    @Column(name = "tenant_enabled")
    private boolean tenantEnabled;

    @Column(name = "auditable")
    private boolean auditable;

    @Column(name = "cacheable")
    private boolean cacheable;

    @Column(name = "api_path", length = 255)
    private String apiPath;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Trường cấu hình JSON mà chúng ta dùng Converter ở trên
    @Convert(converter = JsonMapConverter.class)
    @Column(name = "layout_config", columnDefinition = "TEXT")
    private Map<String, Object> layoutConfig;
}