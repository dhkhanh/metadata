package com.ebiz.metadata.api.schema;

import com.ebiz.metadata.application.schema.command.EntityCommandService;
import com.ebiz.metadata.application.schema.dto.EntityDto;
import com.ebiz.metadata.application.schema.query.EntityQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/entity-definitions")
@RequiredArgsConstructor
public class EntityDefinitionController {

    private final EntityCommandService entityCommandService;
    private final EntityQueryService entityQueryService;

    /**
     * Danh sách Entity
     */
    @GetMapping
    public ResponseEntity<List<EntityDto>> findAll() {

        return ResponseEntity.ok(
                entityQueryService.findAll()
        );
    }

    /**
     * Chi tiết Entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                entityQueryService.getById(id)
        );
    }

    /**
     * Tìm theo entityCode
     */
    @GetMapping("/code/{entityCode}")
    public ResponseEntity<EntityDto> getByCode(
            @PathVariable String entityCode
    ) {

        return ResponseEntity.ok(
                entityQueryService.getByCode(entityCode)
        );
    }

    /**
     * Tạo mới Entity
     */
    @PostMapping
    public ResponseEntity<EntityDto> create(
            @RequestBody EntityDto request
    ) {

        EntityDto result =
                entityCommandService.create(
                        request.getEntityCode(),
                        request.getEntityName(),
                        request.getEntityClass(),
                        request.getServiceName(),
                        request.getSchemaName(),
                        request.getTableName(),
                        request.getDisplayNameField(),
                        request.getDescription()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Cập nhật
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityDto> update(
            @PathVariable UUID id,
            @RequestBody EntityDto request
    ) {

        EntityDto result =
                entityCommandService.update(
                        id,
                        request.getEntityName(),
                        request.getDisplayNameField(),
                        request.getDescription(),
                        request.getActive()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Soft Delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        entityCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Kích hoạt lại
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activate(
            @PathVariable UUID id
    ) {

        entityCommandService.activate(id);

        return ResponseEntity.ok().build();
    }
}