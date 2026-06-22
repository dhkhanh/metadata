package com.ebiz.metadata.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "ebiz.storage")
public class StorageProperties {

    private String bucketName;
    private String maxFileSize;
    
}