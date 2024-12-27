package com.example.FinalAssignment.model.mapper;

import com.example.FinalAssignment.model.dto.ResponseTokensDto;

public class ResponseTokensMapper {
    public static ResponseTokensDto toResponseTokensDto(String token, Long expiration) {
        return new ResponseTokensDto(token, expiration);
    }
}
