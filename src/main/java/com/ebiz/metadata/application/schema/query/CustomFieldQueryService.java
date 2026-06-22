// CustomFieldQueryService sẽ cung cấp các phương thức truy vấn dữ liệu Custom Field, bao gồm:
// - Read Only
// - SELECT
// - SEARCH
// - FILTER
// - PAGING
// - SORTING
// không được UPDATE/INSERT/DELETE

package com.ebiz.metadata.application.schema.query;

import com.ebiz.metadata.application.schema.dto.CustomFieldDto;
import com.ebiz.metadata.domain.schema.model.EntityCustomField;
import com.ebiz.metadata.domain.schema.repository.CustomFieldRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomFieldQueryService {

    private final CustomFieldRepository customFieldRepository;

    public CustomFieldDto getById(UUID id) {

        EntityCustomField field =
                customFieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Custom Field not found : " + id
                                ));

        return CustomFieldDto.from(field);
    }

    public List<CustomFieldDto> findByEntityCode(
            String entityCode
    ) {

        return customFieldRepository
                .findByEntityCode(entityCode)
                .stream()
                .map(CustomFieldDto::from)
                .toList();
    }

    public List<CustomFieldDto> findActiveByEntityCode(
            String entityCode
    ) {

        return customFieldRepository
                .findActiveByEntityCode(entityCode)
                .stream()
                .map(CustomFieldDto::from)
                .toList();
    }

}