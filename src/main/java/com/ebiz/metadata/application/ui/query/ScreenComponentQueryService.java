package com.ebiz.metadata.application.ui.query;

import com.ebiz.metadata.application.ui.dto.ScreenComponentDto;
import com.ebiz.metadata.domain.ui.repository.ScreenComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScreenComponentQueryService {

    private final ScreenComponentRepository repository;

    public List<ScreenComponentDto> findAll() {

        return repository.findAll()
                .stream()
                .map(ScreenComponentDto::from)
                .toList();
    }

    public ScreenComponentDto getById(
            UUID id
    ) {

        return repository.findById(id)
                .map(ScreenComponentDto::from)
                .orElseThrow();
    }

    public List<ScreenComponentDto> findByScreenId(
            UUID screenId
    ) {

        return repository.findByScreenId(screenId)
                .stream()
                .map(ScreenComponentDto::from)
                .toList();
    }
}