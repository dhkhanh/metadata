package com.ebiz.metadata.api.schema;

import com.ebiz.metadata.application.schema.command.CustomFieldCommandService;
import com.ebiz.metadata.application.schema.dto.CustomFieldDto;
import com.ebiz.metadata.application.schema.query.CustomFieldQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/custom-fields")
@RequiredArgsConstructor
public class CustomFieldController {

    private final CustomFieldCommandService customFieldCommandService;
    private final CustomFieldQueryService customFieldQueryService;

    /*** Lấy tất cả Custom Field */
    @GetMapping
    public ResponseEntity<List<CustomFieldDto>> findAll() {

        return ResponseEntity.ok(
                customFieldQueryService.findAll()
        );
    }

    /*** Lấy Custom Field theo ID */
    @GetMapping("/{id}")
    public ResponseEntity<CustomFieldDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                customFieldQueryService.getById(id)
        );
    }

    /*** Lấy danh sách Custom Field của Entity */
    @GetMapping("/entity/{entityCode}")
    public ResponseEntity<List<CustomFieldDto>> findByEntityCode(
            @PathVariable String entityCode
    ) {

        return ResponseEntity.ok(
                customFieldQueryService.findByEntityCode(entityCode)
        );
    }

    /*** Chỉ lấy các Custom Field đang active */
    @GetMapping("/entity/{entityCode}/active")
    public ResponseEntity<List<CustomFieldDto>> findActiveByEntityCode(
            @PathVariable String entityCode
    ) {

        return ResponseEntity.ok(
                customFieldQueryService.findActiveByEntityCode(entityCode)
        );
    }

    /*** Tạo Custom Field */
    @PostMapping
    public ResponseEntity<CustomFieldDto> create(
            @RequestBody CustomFieldDto request
    ) {

        CustomFieldDto result =
                customFieldCommandService.create(
                        request.getEntityCode(),
                        request.getFieldCode(),
                        request.getFieldLabel(),
                        request.getDataType(),
                        request.getRequired(),
                        request.getSearchable(),
                        request.getVisible(),
                        request.getDefaultValue(),
                        request.getDisplayOrder(),
                        request.getDescription()
                );

        return ResponseEntity.ok(result);
    }

    /*** Cập nhật Custom Field */
    @PutMapping("/{id}")
    public ResponseEntity<CustomFieldDto> update(
            @PathVariable UUID id,
            @RequestBody CustomFieldDto request
    ) {

        CustomFieldDto result =
                customFieldCommandService.update(
                        id,
                        request.getFieldLabel(),
                        request.getRequired(),
                        request.getSearchable(),
                        request.getVisible(),
                        request.getDefaultValue(),
                        request.getDisplayOrder(),
                        request.getDescription(),
                        request.getActive()
                );

        return ResponseEntity.ok(result);
    }

    /*** Soft Delete */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        customFieldCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*** Kích hoạt lại */
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activate(
            @PathVariable UUID id
    ) {

        customFieldCommandService.activate(id);

        return ResponseEntity.ok().build();
    }
}