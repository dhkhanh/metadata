// FieldQueryService sẽ cung cấp các phương thức truy vấn dữ liệu Field, bao gồm:
// - Read Only
// - SELECT
// - SEARCH
// - FILTER
// - PAGING
// - SORTING
// không được UPDATE/INSERT/DELETE

package com.ebiz.metadata.application.schema.query;

import com.ebiz.metadata.application.schema.dto.FieldDto;
import com.ebiz.metadata.domain.schema.model.FieldDefinition;
import com.ebiz.metadata.domain.schema.repository.FieldRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FieldQueryService {

    private final FieldRepository fieldRepository;

    public FieldDto getById(UUID id) {

        FieldDefinition field =
                fieldRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Field not found : " + id
                                ));

        return FieldDto.from(field);
    }

    public List<FieldDto> findByEntityCode(
            String entityCode
    ) {

        return fieldRepository
                .findByEntityCode(entityCode)
                .stream()
                .map(FieldDto::from)
                .toList();
    }

    public List<FieldDto> findAll() {

        return fieldRepository.findAll()
                .stream()
                .map(FieldDto::from)
                .toList();
    }

}