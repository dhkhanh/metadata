// CustomFieldCommandService sẽ quản lý CRUD cho các trường động (custom field) được người dùng bổ sung vào các Core Business Entity như:
// employee, customer, vendor, product, document, voucher ...

package com.ebiz.metadata.application.schema.command;

import com.ebiz.metadata.application.schema.dto.CustomFieldDto;
import com.ebiz.metadata.domain.schema.model.EntityCustomField;
import com.ebiz.metadata.domain.schema.repository.CustomFieldRepository;
import com.ebiz.metadata.domain.schema.repository.EntityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomFieldCommandService {

    private final CustomFieldRepository customFieldRepository;
    private final EntityRepository entityRepository;

    /*** Tạo Custom Field */
    public CustomFieldDto create(
            String entityCode,
            String fieldCode,
            String fieldLabel,
            String dataType,
            Boolean required,
            Boolean searchable,
            Boolean visible,
            String defaultValue,
            Integer displayOrder,
            String description
    ) {

        entityRepository.findByEntityCode(entityCode)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Entity not found: " + entityCode
                        ));

        if (customFieldRepository.existsByEntityCodeAndFieldCode(
                entityCode,
                fieldCode
        )) {
            throw new RuntimeException(
                    "Custom field already exists: " + fieldCode
            );
        }

        EntityCustomField field =
                EntityCustomField.builder()
                        .id(UUID.randomUUID())
                        .entityCode(entityCode)
                        .fieldCode(fieldCode)
                        .fieldLabel(fieldLabel)
                        .dataType(dataType)
                        .required(required)
                        .searchable(searchable)
                        .visible(visible)
                        .defaultValue(defaultValue)
                        .displayOrder(displayOrder)
                        .description(description)
                        .active(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        customFieldRepository.save(field);

        return CustomFieldDto.from(field);
    }

    /**
     * Cập nhật Custom Field
     */
    public CustomFieldDto update(
            UUID id,
            String fieldLabel,
            Boolean required,
            Boolean searchable,
            Boolean visible,
            String defaultValue,
            Integer displayOrder,
            String description,
            Boolean active
    ) {

        EntityCustomField field =
                customFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Custom field not found: " + id
                                ));

        field.setFieldLabel(fieldLabel);
        field.setRequired(required);
        field.setSearchable(searchable);
        field.setVisible(visible);
        field.setDefaultValue(defaultValue);
        field.setDisplayOrder(displayOrder);
        field.setDescription(description);
        field.setActive(active);
        field.setUpdatedAt(LocalDateTime.now());

        customFieldRepository.save(field);

        return CustomFieldDto.from(field);
    }

    /**
     * Soft Delete
     */
    public void delete(UUID id) {

        EntityCustomField field =
                customFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Custom field not found: " + id
                                ));

        field.setActive(false);
        field.setUpdatedAt(LocalDateTime.now());

        customFieldRepository.save(field);
    }

    /**
     * Kích hoạt lại
     */
    public void activate(UUID id) {

        EntityCustomField field =
                customFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Custom field not found: " + id
                                ));

        field.setActive(true);
        field.setUpdatedAt(LocalDateTime.now());

        customFieldRepository.save(field);
    }
}