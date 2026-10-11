package com.vaibhav.linksnap.service;

import com.vaibhav.linksnap.dto.ShortenRequestDTO;
import com.vaibhav.linksnap.dto.ShortenResponseDTO;
import com.vaibhav.linksnap.entity.ShortUrl;
import com.vaibhav.linksnap.exception.CodeGenerationException;
import com.vaibhav.linksnap.exception.ResourceNotFoundException;
import com.vaibhav.linksnap.repository.ShortUrlRepository;
import com.vaibhav.linksnap.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private static final int MAX_RETRIES = 10;

    @Transactional
    public ShortenResponseDTO shortenUrl(ShortenRequestDTO requestDTO , String baseUrl) {
        String code = generateUniqueCode();

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setCode(code);
        shortUrl.setOriginalUrl(requestDTO.getOriginalUrl());

        ShortUrl savedUrl = shortUrlRepository.save(shortUrl);

        return ShortenResponseDTO.fromEntity(savedUrl , baseUrl);
    }

    private String generateUniqueCode() {
        for(int i = 0; i < MAX_RETRIES; i++) {
            String code = CodeGenerator.generate();
            if(!shortUrlRepository.existsByCode(code)) {
                return code;
            }
        }

        throw new CodeGenerationException("Failed to generate unique short code after " + MAX_RETRIES + " attempts.");
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String code) {

        return shortUrlRepository.findByCode(code)
                .map(ShortUrl::getOriginalUrl)
                .orElseThrow(() -> new ResourceNotFoundException("Short URL not found for code : " + code));
    }


}
