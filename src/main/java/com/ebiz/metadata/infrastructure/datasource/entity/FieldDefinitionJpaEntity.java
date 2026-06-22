package com.ebiz.metadata.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "field_definition")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class FieldDefinitionJpaEntity {
    @Id private UUID id;
    @Column(nullable = false, length = 100) private String entityCode;
    @Column(nullable = false, length = 100) private String fieldName;
    @Column(nullable = false) private String fieldLabel;
    @Column(nullable = false, length = 50) private String dataType;
    private boolean required;
    private boolean uniqueField;
    private boolean searchable;
    private boolean sortable;
    private boolean visible;
    private int displayOrder;
    @Column(columnDefinition = "TEXT") private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}