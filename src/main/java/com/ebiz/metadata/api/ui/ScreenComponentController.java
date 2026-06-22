package com.ebiz.metadata.api.ui;

import com.ebiz.metadata.application.ui.command.ScreenComponentCommandService;
import com.ebiz.metadata.application.ui.dto.ScreenComponentDto;
import com.ebiz.metadata.application.ui.query.ScreenComponentQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/metadata/screen-components")
@RequiredArgsConstructor
public class ScreenComponentController {

    private final ScreenComponentCommandService commandService;
    private final ScreenComponentQueryService queryService;

    @GetMapping
    public List<ScreenComponentDto> findAll() {
        return queryService.findAll();
    }

    @GetMapping("/{id}")
    public ScreenComponentDto getById(
            @PathVariable UUID id
    ) {
        return queryService.getById(id);
    }

    @GetMapping("/screen/{screenId}")
    public List<ScreenComponentDto> findByScreenId(
            @PathVariable UUID screenId
    ) {
        return queryService.findByScreenId(screenId);
    }

    @PostMapping
    public ResponseEntity<ScreenComponentDto> create(
            @RequestBody ScreenComponentDto dto
    ) {

        return ResponseEntity.ok(
                commandService.create(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenComponentDto> update(
            @PathVariable UUID id,
            @RequestBody ScreenComponentDto dto
    ) {

        return ResponseEntity.ok(
                commandService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        commandService.delete(id);

        return ResponseEntity.noContent().build();
    }
}