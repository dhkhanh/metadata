package com.ebiz.metadata.api.ui;

import com.ebiz.metadata.application.ui.command.FormCommandService;
import com.ebiz.metadata.application.ui.dto.FormDto;
import com.ebiz.metadata.application.ui.query.FormQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/forms")
@RequiredArgsConstructor
public class FormController {

    private final FormCommandService formCommandService;
    private final FormQueryService formQueryService;

    /**
     * Danh sách Form
     */
    @GetMapping
    public ResponseEntity<List<FormDto>> findAll() {

        return ResponseEntity.ok(
                formQueryService.findAll()
        );
    }

    /**
     * Chi tiết Form
     */
    @GetMapping("/{id}")
    public ResponseEntity<FormDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                formQueryService.getById(id)
        );
    }

    /**
     * Danh sách Form theo Entity
     */
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<FormDto>> findByEntityId(
            @PathVariable UUID entityId
    ) {

        return ResponseEntity.ok(
                formQueryService.findByEntityId(entityId)
        );
    }

    /**
     * Tạo Form
     */
    @PostMapping
    public ResponseEntity<FormDto> create(
            @RequestBody FormDto request
    ) {

        FormDto result =
                formCommandService.create(
                        request.getEntityId(),
                        requrest.getFormCode(),
                        request.getName(),
                        request.getFieldsConfig(),
                        request.getLayoutConfig()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Cập nhật Form
     */
    @PutMapping("/{id}")
    public ResponseEntity<FormDto> update(
            @PathVariable UUID id,
            @RequestBody FormDto request
    ) {

        FormDto result =
                formCommandService.update(
                        id,
                        request.getFormCode(),
                        request.getName(),
                        request.getFieldsConfig(),
                        request.getLayoutConfig()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Xóa Form
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        formCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }
}