package com.ebiz.metadata.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "entity_custom_field")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomFieldJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false, length = 100) private String entityCode;
    @Column(nullable = false, length = 100) private String fieldCode;
    @Column(nullable = false) private String fieldLabel;
    @Column(nullable = false, length = 50) private String dataType;
    private boolean required;
    private boolean searchable;
    private boolean visible;
    @Column(columnDefinition = "TEXT") private String defaultValue;
    private int displayOrder;
    @Column(columnDefinition = "TEXT") private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}