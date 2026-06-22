package com.ebiz.metadata.api.schema;

import com.ebiz.metadata.application.schema.command.FieldCommandService;
import com.ebiz.metadata.application.schema.dto.FieldDto;
import com.ebiz.metadata.application.schema.query.FieldQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/field-definitions")
@RequiredArgsConstructor
public class FieldController {

    private final FieldCommandService fieldCommandService;
    private final FieldQueryService fieldQueryService;

    /*** Danh sách tất cả field */
    @GetMapping
    public ResponseEntity<List<FieldDto>> findAll() {

        return ResponseEntity.ok(
                fieldQueryService.findAll()
        );
    }

    /*** Danh sách field theo entity */
    @GetMapping("/entity/{entityCode}")
    public ResponseEntity<List<FieldDto>> findByEntityCode(
            @PathVariable String entityCode
    ) {

        return ResponseEntity.ok(
                fieldQueryService.findByEntityCode(entityCode)
        );
    }

    /*** Chi tiết field */     
    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                fieldQueryService.getById(id)
        );
    }

    /*** Tạo field */
    @PostMapping
    public ResponseEntity<FieldDto> create(
            @RequestBody FieldDto request
    ) {

        FieldDto result =
                fieldCommandService.create(
                        request.getEntityCode(),
                        request.getFieldName(),
                        request.getFieldLabel(),
                        request.getDataType(),
                        request.getRequired(),
                        request.getUniqueField(),
                        request.getSearchable(),
                        request.getSortable(),
                        request.getVisible(),
                        request.getDisplayOrder(),
                        request.getDescription()
                );

        return ResponseEntity.ok(result);
    }

    /*** Cập nhật field */
    @PutMapping("/{id}")
    public ResponseEntity<FieldDto> update(
            @PathVariable UUID id,
            @RequestBody FieldDto request
    ) {

        FieldDto result =
                fieldCommandService.update(
                        id,
                        request.getFieldLabel(),
                        request.getRequired(),
                        request.getUniqueField(),
                        request.getSearchable(),
                        request.getSortable(),
                        request.getVisible(),
                        request.getDisplayOrder(),
                        request.getDescription()
                );

        return ResponseEntity.ok(result);
    }

    /*** Xóa field */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        fieldCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }
}