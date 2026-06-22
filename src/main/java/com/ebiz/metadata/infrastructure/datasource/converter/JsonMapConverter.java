package com.ebiz.metadata.infrastructure.persistence.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Map;

@Converter
public class JsonMapConverter implements AttributeConverter<Map<String, Object>, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return (attribute == null) ? null : objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi chuyển đổi Map sang JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return (dbData == null) ? null : objectMapper.readValue(dbData, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Lỗi chuyển đổi JSON sang Map", e);
        }
    }
}