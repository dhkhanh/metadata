package com.ebiz.metadata.api.ui;

import com.ebiz.metadata.application.ui.command.ScreenCommandService;
import com.ebiz.metadata.application.ui.dto.ScreenDto;
import com.ebiz.metadata.application.ui.query.ScreenQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenCommandService screenCommandService;
    private final ScreenQueryService screenQueryService;

    /**
     * Danh sách Screen
     */
    @GetMapping
    public ResponseEntity<List<ScreenDto>> findAll() {

        return ResponseEntity.ok(
                screenQueryService.findAll()
        );
    }

    /**
     * Chi tiết Screen
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScreenDto> getById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                screenQueryService.getById(id)
        );
    }

    /**
     * Tìm theo Permission Code
     */
    @GetMapping("/permission/{permissionCode}")
    public ResponseEntity<ScreenDto> getByPermissionCode(
            @PathVariable String permissionCode
    ) {

        return ResponseEntity.ok(
                screenQueryService.getByPermissionCode(
                        permissionCode
                )
        );
    }

    /**
     * Tạo Screen
     */
    @PostMapping
    public ResponseEntity<ScreenDto> create(
            @RequestBody ScreenDto request
    ) {

        ScreenDto result =
                screenCommandService.create(
                        request.getScreenCode(),
                        request.getName(),
                        request.getComponents(),
                        request.getPermissionCode()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Cập nhật Screen
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScreenDto> update(
            @PathVariable UUID id,
            @RequestBody ScreenDto request
    ) {

        ScreenDto result =
                screenCommandService.update(
                        id,
                        request.getScreenCode(),
                        request.getName(),
                        request.getComponents(),
                        request.getPermissionCode()
                );

        return ResponseEntity.ok(result);
    }

    /**
     * Xóa Screen
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        screenCommandService.delete(id);

        return ResponseEntity.noContent().build();
    }
    // GET /api/metadata/screens/code/INVENTORY_RECEIPT
    @GetMapping("/code/{screenCode}")
    public ResponseEntity<ScreenDto> getByScreenCode(@PathVariable String screenCode){
        return ResponseEntity.ok(screenQueryService.getByScreenCode(screenCode));
    }
}