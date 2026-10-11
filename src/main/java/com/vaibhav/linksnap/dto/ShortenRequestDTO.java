package com.vaibhav.linksnap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortenRequestDTO {

    @NotBlank(message = "OriginalUrl cannot be blank")
    @URL(message = "Invalid URL Format")
    private String originalUrl;
}
