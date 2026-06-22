// FieldCommandService sẽ quản lý CRUD cho bảng: field_definition
// - Tạo Field Definition
// - Cập nhật Field Definition
// - Xóa Field Definition
// - Validate Entity tồn tại trước khi thêm field

package com.ebiz.metadata.application.schema.command;

import com.ebiz.metadata.application.schema.dto.FieldDto;
import com.ebiz.metadata.domain.schema.model.EntityDefinition;
import com.ebiz.metadata.domain.schema.model.FieldDefinition;
import com.ebiz.metadata.domain.schema.repository.EntityRepository;
import com.ebiz.metadata.domain.schema.repository.FieldRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FieldCommandService {

    private final FieldRepository fieldRepository;
    private final EntityRepository entityRepository;

    /*** Tạo field mới */
    public FieldDto create(
            String entityCode,
            String fieldName,
            String fieldLabel,
            String dataType,
            boolean required,
            boolean uniqueField,
            boolean searchable,
            boolean sortable,
            boolean visible,
            Integer displayOrder,
            String description
    ) {

        EntityDefinition entity =
                entityRepository.findByEntityCode(entityCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Entity not found: " + entityCode
                                ));

        if (fieldRepository.existsByEntityCodeAndFieldName(
                entityCode,
                fieldName
        )) {
            throw new RuntimeException(
                    "Field already exists: " + fieldName
            );
        }

        FieldDefinition field = FieldDefinition.builder()
                .id(UUID.randomUUID())
                .entityCode(entityCode)
                .fieldName(fieldName)
                .fieldLabel(fieldLabel)
                .dataType(dataType)
                .required(required)
                .uniqueField(uniqueField)
                .searchable(searchable)
                .sortable(sortable)
                .visible(visible)
                .displayOrder(displayOrder)
                .description(description)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        fieldRepository.save(field);

        return FieldDto.from(field);
    }

    /*** Cập nhật field */
    public FieldDto update(
            UUID id,
            String fieldLabel,
            boolean required,
            boolean uniqueField,
            boolean searchable,
            boolean sortable,
            boolean visible,
            Integer displayOrder,
            String description
    ) {

        FieldDefinition field =
                fieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Field not found: " + id
                                ));

        field.setFieldLabel(fieldLabel);
        field.setRequired(required);
        field.setUniqueField(uniqueField);
        field.setSearchable(searchable);
        field.setSortable(sortable);
        field.setVisible(visible);
        field.setDisplayOrder(displayOrder);
        field.setDescription(description);
        field.setUpdatedAt(LocalDateTime.now());

        fieldRepository.save(field);

        return FieldDto.from(field);
    }

    /*** Xóa field */
    public void delete(UUID id) {

        FieldDefinition field =
                fieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Field not found: " + id
                                ));

        fieldRepository.delete(field);
    }

}