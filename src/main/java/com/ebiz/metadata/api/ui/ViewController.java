package com.ebiz.metadata.api.ui;

import com.ebiz.metadata.application.ui.command.ViewCommandService;
import com.ebiz.metadata.application.ui.dto.ViewDto;
import com.ebiz.metadata.application.ui.query.ViewQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/views")
@RequiredArgsConstructor
public class ViewController {

    private final ViewCommandService viewCommandService;
    private final ViewQueryService viewQueryService;

    /**
     * Danh sách View
     */
    @GetMapping
    public ResponseEntity<List<ViewDto>> findAll() {

        return ResponseEntity.ok(
                viewQueryService.findAll()
        );
    }

    /**
     * Chi tiết View
     */
    @GetMapping("/{id}")
    public ResponseEntity<ViewDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                viewQueryService.getById(id)
        );
    }

    /**
     * Danh sách View theo Entity
     */
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<ViewDto>> findByEntityId(
            @PathVariable UUID entityId
    ) {

        return ResponseEntity.ok(
                viewQueryService.findByEntityId(entityId)
        );
    }

    /**
     * Tạo View
     */
    @PostMapping
    public ResponseEntity<ViewDto> create(
            @RequestBody ViewDto request
    ) {

        ViewDto result =
                viewCommandService.create(
                        request.getEntityId(),
                        request.getViewCode(),
                        request.getName(),
                        request.getColumnsConfig(),
                        request.getFilterConfig()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Cập nhật View
     */
    @PutMapping("/{id}")
    public ResponseEntity<ViewDto> update(
            @PathVariable UUID id,
            @RequestBody ViewDto request
    ) {

        ViewDto result =
                viewCommandService.update(
                        id,
                        request.getViewCode(),
                        request.getName(),
                        request.getColumnsConfig(),
                        request.getFilterConfig()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Xóa View
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        viewCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/code/{viewCode}")
    public ResponseEntity<ViewDto> getByCode(@PathVariable String viewCode){
        return ResponseEntity.ok(
                viewQueryService.getByViewCode(viewCode)
        );
    }
}