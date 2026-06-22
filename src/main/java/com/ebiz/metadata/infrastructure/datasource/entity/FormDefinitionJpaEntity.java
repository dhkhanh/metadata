package com.ebiz.metadata.infrastructure.persistence.entity;

import com.ebiz.metadata.infrastructure.persistence.converter.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Entity
@Table(
    name = "form_definition",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_form_definition_code",
            columnNames = "form_code"
        )
    }
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class FormDefinitionJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private UUID entityId;
    @Column(
        name = "form_code",
        nullable = false,
        unique = true,
        length = 100
    )
    private String formCode;
    @Column(nullable = false) private String name;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT") private Map<String, Object> fieldsConfig;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT") private Map<String, Object> layoutConfig;

    private LocalDateTime createdAt;
}