package com.ebiz.metadata.infrastructure.persistence.entity;

import com.ebiz.metadata.infrastructure.persistence.converter.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Entity
@Table(
    name="view_definition",
    uniqueConstraints = {
        @UniqueConstraint(
            name="uk_view_definition_code",
            columnNames="view_code"
        )
    }
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ViewDefinitionJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private UUID entityId;
     @Column(
        name="view_code",
        nullable=false,
        unique=true,
        length=100
    )
    private String viewCode;
    @Column(nullable = false) private String name;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT") private Map<String, Object> columnsConfig;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT") private Map<String, Object> filterConfig;

    private LocalDateTime createdAt;
}