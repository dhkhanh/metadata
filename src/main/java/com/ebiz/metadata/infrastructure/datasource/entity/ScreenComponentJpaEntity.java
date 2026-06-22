package com.ebiz.metadata.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "screen_component")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenComponentJpaEntity {

    @Id
    private UUID id;

    @Column(name = "screen_id", nullable = false)
    private UUID screenId;

    @Column(name = "component_type")
    private String componentType;

    @Column(name = "component_id", nullable = false)
    private UUID componentId;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "region")
    private String region;
}