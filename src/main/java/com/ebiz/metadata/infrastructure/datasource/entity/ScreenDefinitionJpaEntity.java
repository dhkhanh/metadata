package com.ebiz.metadata.infrastructure.persistence.entity;

import com.ebiz.metadata.infrastructure.persistence.converter.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
@Entity
@Table(
        name="screen_definition",
        uniqueConstraints = {
            @UniqueConstraint(
                    name="uk_screen_definition_code",
                    columnNames="screen_code"
            )
        }
)
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ScreenDefinitionJpaEntity {
    @Id private UUID id;
    @Column(
            name="screen_code",
            nullable=false,
            unique=true,
            length=100
    )
    private String screenCode;
    @Column(nullable = false) private String name;

    @Convert(converter = JsonMapConverter.class)
    @Column(columnDefinition = "TEXT") private Map<String, Object> components;

    @Column(length = 100) private String permissionCode;
    private LocalDateTime createdAt;
}