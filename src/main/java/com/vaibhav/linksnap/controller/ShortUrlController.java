package com.vaibhav.linksnap.controller;

import com.vaibhav.linksnap.dto.ShortenRequestDTO;
import com.vaibhav.linksnap.dto.ShortenResponseDTO;
import com.vaibhav.linksnap.service.ShortUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ShortUrlController {

    private final ShortUrlService shortUrlService;


    @PostMapping("/api/v1/urls")
    public ResponseEntity<ShortenResponseDTO> creation(
            @Valid @RequestBody ShortenRequestDTO requestDTO) {

        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .build().toUriString();

        ShortenResponseDTO responseDTO = shortUrlService.shortenUrl(requestDTO , baseUrl);

        return new ResponseEntity<>(responseDTO , HttpStatus.CREATED);

    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirection (@PathVariable String code) {

        String originalUrl = shortUrlService.getOriginalUrl(code);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

}















