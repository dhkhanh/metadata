// EntityQueryService sẽ cung cấp các phương thức truy vấn dữ liệu Entity, bao gồm:
// - Read Only
// - SELECT
// - SEARCH
// - FILTER
// - PAGING
// - SORTING
// không được UPDATE/INSERT/DELETE

package com.erp.metadata.application.schema.query;

import com.erp.metadata.application.schema.dto.EntityDto;
import com.erp.metadata.domain.schema.model.EntityDefinition;
import com.erp.metadata.domain.schema.repository.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntityQueryService {

    private final EntityRepository entityRepository;

    public EntityDto getById(UUID id) {

        EntityDefinition entity =
                entityRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Entity not found : " + id
                                ));

        return EntityDto.from(entity);
    }

    public EntityDto getByCode(String entityCode) {

        EntityDefinition entity =
                entityRepository.findByEntityCode(entityCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Entity not found : " + entityCode
                                ));

        return EntityDto.from(entity);
    }

    public List<EntityDto> findAll() {

        return entityRepository.findAll()
                .stream()
                .map(EntityDto::from)
                .toList();
    }

    public List<EntityDto> findActive() {

        return entityRepository.findByActiveTrue()
                .stream()
                .map(EntityDto::from)
                .toList();
    }

}