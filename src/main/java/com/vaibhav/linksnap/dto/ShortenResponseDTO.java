package com.vaibhav.linksnap.dto;

import com.vaibhav.linksnap.entity.ShortUrl;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortenResponseDTO {

    private String code;
    private String shortUrl;
    private String originalUrl;
    private Instant createdAt;
    private Instant updatedAt;

    public static ShortenResponseDTO fromEntity (ShortUrl entity , String baseUrl) {

        String fullShortUrl = (baseUrl != null && !baseUrl.isBlank())
                ? baseUrl + '/' + entity.getCode()
                : entity.getCode();
            return ShortenResponseDTO.builder()
                    .code(entity.getCode())
                    .shortUrl(fullShortUrl)
                    .originalUrl(entity.getOriginalUrl())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build();
    }

}
