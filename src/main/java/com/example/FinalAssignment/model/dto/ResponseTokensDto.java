package com.example.FinalAssignment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class ResponseTokensDto {
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private Long accessTokenExpiresIn;


    public ResponseTokensDto(String accessToken, Long accessTokenExpiresIn) {
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
